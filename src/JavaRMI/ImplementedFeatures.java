import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;


/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuidos
 */
public class ImplementedFeatures extends UnicastRemoteObject implements Features {

    private  final static int coins = 10000;
    private ConnectionPool pool;

    ImplementedFeatures() throws RemoteException {
        super();
    }

    public void CreateRMIServer() throws RemoteException {
        try {
            System.getProperties().put("java.security.policy", "policy.all");
            //System.setSecurityManager(new RMISecurityManager());
            Registry r = LocateRegistry.createRegistry(7000);
            r.rebind("IdeaBroker", this);
            ServerRMI.text.append("\n A RMI server has been created");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failure in creating the RMI server: \" + e");
            throw new RemoteException();
        }
    }

    public void ConnectDatabase() throws RemoteException {        //connect to database
        try {
            //FIXME: ADD THE FILE TO THE EXTERNAL LIBRARIES, HOW TO CREATE THE .JAR
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Class not found:\n" + e);
        }
        try {
            pool = new ConnectionPool();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to make connection:\n" + e);
            throw new RemoteException();
        }
    }

    /**
     * Function to handle the Login of the user.
     *
     * @param user the username
     * @param pass the password
     * @return -1 if incorrect password
     *         <p>0 if user is incorrect or do not exists</p>
     *         <p>integer representing the user id in case of success</p>
     * @throws RemoteException
     */
    public int Login(String user, String pass) throws RemoteException {
        int returningvalue = 2;
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT * FROM tbl_user WHERE username LIKE '" + user + "'");
            if (queryresult.first()) {
                String tablepass = queryresult.getString(3);
                if (tablepass.equals(pass)) {
                    //password is correct
                    returningvalue = Integer.parseInt(queryresult.getString(1));
                }else{
                    //password is incorrect
                    returningvalue = -1;
                }
            } else {
                //create a user
                returningvalue = 0;
            }
            pool.releaseConnection(conn);
            return returningvalue;
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL exception:\n" + e);
        }
        //error
        return returningvalue;
    }

    /**
     * @param user the username
     * @return 1 in case of success, -1 in case of error, 0 if the user already exists
     * @throws RemoteException
     */
    public int newUser(ArrayList<String> user) throws RemoteException {
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT * FROM tbl_user WHERE username LIKE '" + user.get(0) + "'");
            if (queryresult.first()) {
                pool.releaseConnection(conn);
                return 0;
            } else {
                statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                statement.executeQuery("INSERT INTO tbl_user VALUES(User_seq.nextval,'" + user.get(0) + "','" + user.get(1) + "','" + user.get(2) + "','" + user.get(3) + "','" + user.get(4) + "','" + coins + "', SYSTIMESTAMP)");
                pool.releaseConnection(conn);
                return 1;
            }
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL exception:");
        }
        //error
        return -1;
    }

    public int newIdea(ArrayList<String> data, int id_user, boolean ischild) throws RemoteException {
        int size = data.size();
        int ID_idea;
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT user_ideas.id_user, ideas.id_idea,ideas.description FROM Ideas, User_Ideas WHERE user_ideas.id_idea = ideas.id_idea AND ideas.Title LIKE '" + data.get(size - 4) + "'");
            if (queryresult.first()) {
                int aux = 0;
                if (queryresult.getInt(1)==id_user && data.get(size - 3).equals(queryresult.getString(3))){
                    aux = queryresult.getInt(2);
                }
                pool.releaseConnection(conn);
                return aux;
            } else {
                conn.setAutoCommit(false);
                statement.executeQuery("INSERT INTO Ideas VALUES(Ideas_seq.nextval,'" + data.get(size - 4) + "','" + data.get(size - 3) + "','F')");
                queryresult = statement.executeQuery("SELECT * FROM Ideas WHERE Title LIKE '" + data.get(size - 4) + "'");
                if (queryresult.first()) {
                    ID_idea = queryresult.getInt(1);
                    statement.executeQuery("INSERT INTO user_ideas VALUES(" + id_user + "," + ID_idea + ",100," + data.get(size - 2) + ")");
                    int i = 0;
                    if (ischild) {
                        queryresult = statement.executeQuery("SELECT * FROM Ideas WHERE Title LIKE '" + data.get(0) + "'");
                        queryresult.first();
                        statement.executeQuery("INSERT INTO relation_ideas VALUES(" + queryresult.getInt(1) + "," + ID_idea + "," + data.get(size - 1) + ")");
                        i++;
                    }
                    for (; i < (size - 4); i++) {
                        statement.executeQuery("INSERT INTO Topics_Idea VALUES(" + ID_idea + "," + data.get(i) + ")");
                    }
                    conn.commit();
                    conn.setAutoCommit(true);
                    pool.releaseConnection(conn);
                    return ID_idea;
                } else {
                    ServerRMI.text.append("\n ERRO SQL");
                    conn.rollback();
                    pool.releaseConnection(conn);
                    return -1;
                }

            }
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL exception:\n" + e);
        }
        //error
        return -1;
    }

    public int newTopics(ArrayList<String> data) throws RemoteException {
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT * FROM Topics WHERE description LIKE '" + data.get(0) + "'");
            if (queryresult.first()) {
                pool.releaseConnection(conn);
                return queryresult.getInt(1);
            } else {
                statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                statement.executeQuery("INSERT INTO Topics VALUES(Topics_seq.nextval,'" + data.get(0) + "')");
                queryresult = statement.executeQuery("SELECT * FROM Topics WHERE description LIKE '" + data.get(0) + "'");
                if (queryresult.first()) {
                    pool.releaseConnection(conn);
                    return queryresult.getInt(1);
                }
            }
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL exception:" + e.getMessage());
        }
        //error
        return -1;
    }

    public ArrayList<String> getTopics() throws RemoteException {
        ArrayList<String> topics = new ArrayList<String>();
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT * FROM Topics ORDER BY description");
            while (queryresult.next()) {
                topics.add(queryresult.getString(2));
            }
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL EXCEPTION:\n" + e);
            return null;
        }
        return topics;
    }

    public ArrayList<String> getIdeas(String topic) throws RemoteException {
        ArrayList<String> ideas = new ArrayList<String>();
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT DISTINCT I.Title " +
                    "FROM Ideas I,Topics T,Topics_Idea T_I " +
                    "WHERE I.ID_IDEA = T_I.ID_Idea And " +
                    "T.ID_Topics = T_I.ID_Topics AND " +
                    "T.Description Like '" + topic + "' AND " +
                    "I.deleted LIKE 'F' " +
                    "ORDER BY I.title");
            while (queryresult.next()) {
                ideas.add(queryresult.getString(1));
            }
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL EXCEPTION:\n" + e);
            return null;
        }
        return ideas;
    }

    public ArrayList<String> getIdeaDetails(String idea) throws RemoteException {
        ArrayList<String> det_idea = new ArrayList<String>();
        int idea_ID;
        int number_Topics = 0;
        int number_Against = 0;
        int number_Neutral = 0;
        int number_Favor = 0;
        int numberowners = 0;
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT * From Ideas WHERE Ideas.Title Like '" + idea + "'");
            if (queryresult.first()) {
                idea_ID = queryresult.getInt(1);
                det_idea.add(queryresult.getString(2));
                det_idea.add(queryresult.getString(3));
            } else {
                pool.releaseConnection(conn);
                return null;
            }
            //get Topics related to the Idea
            queryresult = statement.executeQuery("SELECT DISTINCT T.Description " +
                    "FROM Topics T,Topics_Idea T_I " +
                    "WHERE T_I.ID_Idea = " + idea_ID + " AND " +
                    "T_I.ID_toPICS = T.ID_toPICS " +
                    "ORDER BY T.Description");
            while (queryresult.next()) {
                number_Topics++;
                det_idea.add(queryresult.getString(1));
            }

            //Get the ideas against the Idea
            queryresult = statement.executeQuery("SELECT I2.Title From  Relation_Ideas R " +
                    "INNER JOIN Ideas I ON I.ID_iDEA = R.Main_Idea " +
                    "INNER JOIN Ideas I2 ON I2.ID_iDEA = R.Slave_Idea " +
                    "WHERE Main_Idea = " + idea_ID + " AND RELATION = -1");
            while (queryresult.next()) {
                number_Against++;
                det_idea.add(queryresult.getString(1));
            }
            //Get the ideas neutral to the Idea
            queryresult = statement.executeQuery("SELECT I2.Title From  Relation_Ideas R " +
                    "INNER JOIN Ideas I ON I.ID_iDEA = R.Main_Idea " +
                    "INNER JOIN Ideas I2 ON I2.ID_iDEA = R.Slave_Idea " +
                    "WHERE Main_Idea = " + idea_ID + " AND RELATION = 0");
            while (queryresult.next()) {
                number_Neutral++;
                det_idea.add(queryresult.getString(1));
            }
            //Get the ideas IN FAVOR to the Idea
            queryresult = statement.executeQuery("SELECT I2.Title From  Relation_Ideas R " +
                    "INNER JOIN Ideas I ON I.ID_iDEA = R.Main_Idea " +
                    "INNER JOIN Ideas I2 ON I2.ID_iDEA = R.Slave_Idea " +
                    "WHERE Main_Idea = " + idea_ID + " AND RELATION = 1");
            while (queryresult.next()) {
                number_Favor++;
                det_idea.add(queryresult.getString(1));
            }

            queryresult = statement.executeQuery("SELECT tbl_user.username From Ideas, User_Ideas, tbl_user " +
                    "WHERE User_Ideas.ID_user = tbl_user.ID_User AND Ideas.Id_idea = User_Ideas.ID_Idea AND Ideas.Title Like '" + idea + "'");
            while (queryresult.next()) {
                numberowners++;
                det_idea.add(queryresult.getString(1));
            }

            det_idea.add(Integer.toString(number_Topics));
            det_idea.add(Integer.toString(number_Against));
            det_idea.add(Integer.toString(number_Neutral));
            det_idea.add(Integer.toString(number_Favor));
            det_idea.add(Integer.toString(numberowners));
            pool.releaseConnection(conn);
            return det_idea;

        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL EXCEPTION:\n" + e);
        }
        return null;
    }

    public ArrayList<String> getSharesInfo(String idea) throws RemoteException {
        ArrayList<String> users = new ArrayList<String>();
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("Select U.username,UI.Percentage, UI.Price from  User_Ideas UI " +
                    "INNER JOIN tbl_user U ON U.ID_User = UI.ID_User " +
                    "INNER JOIN Ideas I ON I.ID_Idea = UI.ID_Idea " +
                    "WHERE I.title like '" + idea + "' " +
                    "ORDER BY UI.Price");
            while (queryresult.next()) {
                users.add(queryresult.getString(1));
                users.add(queryresult.getString(2));
                users.add(queryresult.getString(3));
            }
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL EXCEPTION:\n" + e);

            return null;
        }
        return users;
    }

    public ArrayList<String> buyShares(String idea, int percentage, int ID_User, int sellingPrice, int maxprice, String timeStamp) throws RemoteException {
        int ID_idea;
        int original_percentage = percentage;
        int DEIcoins = percentage * maxprice;
        int ID_Userseller;
        int totalPrice=0;
        ArrayList<String> output = new ArrayList<String>();
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT username FROM tbl_user WHERE ID_User = " + ID_User + "");
            if (queryresult.first()) {
                String userName = queryresult.getString(1);
                //gets the user with the selected shares
                ArrayList<String> users = getSharesInfo(idea);
                //gets the idea ID
                queryresult = statement.executeQuery("SELECT * FROM Ideas WHERE Title LIKE '" + idea + "'");
                if (queryresult.first()) {
                    ID_idea = queryresult.getInt(1);
                    int aux;
                    int aux_percent;

                    conn.setAutoCommit(false);
                    conn.setSavepoint();
                    for (int i = 0; i < users.size() - 1; i = i + 3) {
                        aux = 0;
                        aux_percent=0;
                        //get the seller ID
                        queryresult = statement.executeQuery("SELECT * FROM tbl_User WHERE username LIKE '" + users.get(i) + "'");
                        if (queryresult.first()) {
                            ID_Userseller = queryresult.getInt(1);
                            if ( ID_Userseller == ID_User){
                                continue;
                            }
                            if (Integer.parseInt(users.get(i + 1)) <= percentage) {
                                aux = Integer.parseInt(users.get(i + 2)) * Integer.parseInt(users.get(i + 1));
                                if (aux <= DEIcoins) {
                                    statement.executeQuery("DELETE FROM User_Ideas WHERE ID_User = " + ID_Userseller + " AND ID_Idea = " + ID_idea);
                                    //updates the remaining percentage and the DEI_coins of the client
                                    aux_percent=Integer.parseInt(users.get(i + 1));
                                    percentage = percentage - aux_percent;
                                    DEIcoins = DEIcoins - aux;
                                } else {
                                    //NOT ENOUGH MONEY!!!!!
                                    if (timeStamp==null)
                                        addBuyList(idea,percentage,ID_User,sellingPrice,maxprice);
                                    conn.setAutoCommit(true);
                                    pool.releaseConnection(conn);
                                    return new ArrayList<String>();
                                }
                            } else {
                                if (percentage > 0) {
                                    aux = Integer.parseInt(users.get(i + 2)) * percentage;
                                    if (aux <= DEIcoins) {
                                        statement.executeQuery("UPDATE User_Ideas SET percentage = " + (Integer.parseInt(users.get(i + 1)) - percentage) +
                                                " WHERE ID_User = " + ID_Userseller + " AND ID_Idea = " + ID_idea);
                                        DEIcoins = DEIcoins - aux;
                                        aux_percent = percentage;
                                        percentage = 0;
                                    } else {
                                        //NOT ENOUGH MONEY!!!!!
                                        if (timeStamp==null)
                                            addBuyList(idea,percentage,ID_User,sellingPrice,maxprice);
                                        conn.setAutoCommit(true);
                                        pool.releaseConnection(conn);
                                        return new ArrayList<String>();
                                    }
                                }
                            }
                            //creates the transaction
                            statement.executeQuery("INSERT INTO Transactions VALUES(Transactions_seq.nextval , " + ID_User + " , " + ID_Userseller +
                                    " , " + ID_idea + " , " + aux + " , " + original_percentage + " , SYSTIMESTAMP)");
                            //updates the DEIcoins of the buyer and the seller
                            statement.executeQuery("UPDATE TBL_User SET DEIcoins = DEIcoins + " + aux +
                                    " WHERE ID_User = " + ID_Userseller);
                            statement.executeQuery("UPDATE TBL_User SET DEIcoins = DEIcoins - " + aux +
                                    " WHERE ID_User = " + ID_User);
                            //for the notifications
                            output.add(Integer.toString(ID_Userseller));
                            output.add("The user '"+userName+"' bought "+aux_percent+"% of your idea '"+
                                    idea+"' for "+aux+" DEIcoins .");
                            totalPrice+=aux;
                            if (percentage==0){
                                //check if the user has the idea
                                queryresult = statement.executeQuery("SELECT * FROM User_Ideas WHERE ID_User = " + ID_User + " AND ID_Idea = "+ID_idea);
                                if (queryresult.first()) {
                                    statement.executeQuery("UPDATE User_Ideas SET Percentage = Percentage + " + original_percentage +
                                            " , price = "+sellingPrice+" WHERE ID_User = " + ID_User + " AND ID_Idea = "+ID_idea);
                                }else{
                                    statement.executeQuery("INSERT INTO User_Ideas" +
                                            " VALUES(" + ID_User + " , " + ID_idea + " , " + original_percentage + " , " + sellingPrice+")");
                                }
                                output.add(Integer.toString(totalPrice));
                                conn.commit();
                                conn.setAutoCommit(true);
                                pool.releaseConnection(conn);
                                if (timeStamp==null){
                                    output.addAll(updateBuyList(idea));
                                }
                                return output;
                            }
                        }
                    }
                }
            }
            conn.rollback();
            conn.setAutoCommit(true);
            pool.releaseConnection(conn);
        } catch (Exception e) {
            ServerRMI.text.append("\n SQL exception:\n" + e);
            return null;
        }
        //error
        ServerRMI.text.append("\n ERRO final!");
        return null;
    }

    public ArrayList<String> getTransactions(int ID_user,boolean limited) throws RemoteException{
        ArrayList<String> trans = new ArrayList<String>();
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query;
            if (limited){
                query = "Select T.ID_Buyer, U.username, U2.username, I.Title, T.value, T.Percentage, TO_CHAR(T.Timestamp, 'DD-MON-YYYY HH24:MI:SS'), I.deleted "+
                        "FROM  Transactions T " +
                        "INNER JOIN tbl_user U ON U.ID_User = T.ID_Buyer " +
                        "INNER JOIN tbl_user U2 ON U2.ID_User = T.ID_Seller " +
                        "INNER JOIN Ideas I ON I.ID_Idea = T.ID_Idea " +
                        "WHERE ( U.ID_User = " + ID_user + " OR U2.ID_User = " + ID_user + " ) " +
                        "AND T.Timestamp >= (Select LastLogin FROM tbl_user Where ID_user = " + ID_user + ") " +
                        "ORDER BY T.Timestamp";
            }else{
                query = "Select T.ID_Buyer, U.username, U2.username, I.Title, T.value, T.Percentage, TO_CHAR(T.Timestamp, 'DD-MON-YYYY HH24:MI:SS'), I.deleted " +
                        "FROM  Transactions T " +
                        "INNER JOIN tbl_user U ON U.ID_User = T.ID_Buyer " +
                        "INNER JOIN tbl_user U2 ON U2.ID_User = T.ID_Seller " +
                        "INNER JOIN Ideas I ON I.ID_Idea = T.ID_Idea " +
                        "WHERE U.ID_user = "+ID_user+" OR U2.ID_user = "+ID_user+
                        " ORDER BY T.Timestamp";
            }
            ResultSet queryresult = statement.executeQuery(query);
            String row;
            while (queryresult.next()) {
                if(queryresult.getInt(1)==ID_user){
                    if (queryresult.getString(8).equals("F")){
                      row = "["+queryresult.getString(7)+"]Bought <"+queryresult.getString(6)+"%> of the idea <"+queryresult.getString(4)+
                              "> from the user <"+queryresult.getString(3)+"> for <"+queryresult.getString(5)+" DEIcoins> .";
                    }
                    else{
                        row = "["+queryresult.getString(7)+"]Bought <"+queryresult.getString(6)+"%> of the idea <"+queryresult.getString(4)+
                                "(Deleted)> from the user <"+queryresult.getString(3)+"> for <"+queryresult.getString(5)+" DEIcoins> .";
                    }
                }
                else{
                    if (queryresult.getString(8).equals("F")){
                        row = "["+queryresult.getString(7)+"]Sold <"+queryresult.getString(6)+"%> of the idea <"+queryresult.getString(4)+
                                "> to the user <"+queryresult.getString(2)+"> for <"+queryresult.getString(5)+" DEIcoins> .";
                    }
                    else{
                        row = "["+queryresult.getString(7)+"]Sold <"+queryresult.getString(6)+"%> of the idea <"+queryresult.getString(4)+
                                "(Deleted)> to the user <"+queryresult.getString(2)+"> for <"+queryresult.getString(5)+" DEIcoins> .";
                    }

                }
                trans.add(row);
            }
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL EXCEPTION:\n" + e);
            return null;
        }
        return trans;
    }

    public int updateLastLogin(int ID_User) throws RemoteException {
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeQuery("UPDATE TBL_User SET LastLogin = SYSTIMESTAMP WHERE ID_User = " + ID_User);
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQLException "+e);
            return -1;
        }
        return 1;
    }

    public ArrayList<String> getuserIdeas(int ID_User){
        ArrayList<String> ideas = new ArrayList<String>();
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT DISTINCT I.Title " +
                    "FROM Ideas I, User_Ideas UI "+
                    "WHERE I.ID_Idea = UI.ID_Idea AND " +
                    "UI.ID_User = + " + ID_User + " AND " +
                    "I.deleted LIKE 'F' " +
                    "ORDER BY I.title");
            while (queryresult.next()) {
                ideas.add(queryresult.getString(1));
            }
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL EXCEPTION:\n" + e);
            return null;
        }
        return ideas;
    }

    public int deleteIdeas(String idea,int ID_User) throws RemoteException{
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT * From Ideas WHERE Ideas.Title Like '" + idea + "'");
            if (queryresult.first()) {
                int idea_ID = queryresult.getInt(1);
                queryresult = statement.executeQuery("SELECT percentage From User_Ideas WHERE ID_Idea = " + idea_ID + " AND ID_User = "+ID_User);
                if (queryresult.first()) {
                    int percentage = queryresult.getInt(1);
                    if ( percentage == 100 ){
                        conn.setAutoCommit(false);
                        statement.executeQuery("DELETE FROM Relation_Ideas WHERE" +
                                " Main_Idea = " + idea_ID + " OR Slave_Idea = "+idea_ID);
                        statement.executeQuery("DELETE FROM Topics_Idea WHERE" +
                                " ID_Idea = " + idea_ID);
                        statement.executeQuery("DELETE FROM User_Ideas WHERE" +
                                " ID_Idea = " + idea_ID);
                        statement.executeQuery("UPDATE Ideas SET Deleted = 'T' WHERE ID_Idea = " + idea_ID);
                        conn.commit();
                        pool.releaseConnection(conn);
                        return 1;
                    }
                }
            }
            conn.setAutoCommit(true);
            pool.releaseConnection(conn);
        }catch (SQLException e){
            ServerRMI.text.append("\n SQLException:\n"+e);
        }
        return -1;
    }

    public ArrayList<String> setSharePrice(String idea,int price, int ID_User) throws RemoteException{
        ArrayList<String> returnID = new ArrayList<String>();
        int ID_idea;
        ServerRMI.text.append("\n setsharePrice");
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT * FROM Ideas WHERE Title LIKE '" + idea + "'");
                if (queryresult.first()) {
                    ID_idea = queryresult.getInt(1);
                    statement.executeQuery("UPDATE User_Ideas SET price = " + price + " WHERE ID_User = " + ID_User + " AND ID_Idea = " + ID_idea);
                    conn.commit();
                    conn.setAutoCommit(true);
                    pool.releaseConnection(conn);
                    returnID = updateBuyList(idea);
                    return returnID;
                }
            conn.setAutoCommit(true);
            pool.releaseConnection(conn);
        } catch (Exception e) {
            ServerRMI.text.append("\n SQL exception:\n" + e);
            return returnID;
        }
        //error
        return returnID;
    }

    public int getCoins(int ID_User) throws RemoteException {
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT * FROM tbl_User WHERE ID_User = "+ID_User);
            if (queryresult.first()){
                pool.releaseConnection(conn);
                return queryresult.getInt(7);
            }
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL EXCEPTION:\n" + e);
            return -1;
        }
        return -1;
    }

    public int saveFile(int idea, String filename, byte[] file) throws RemoteException{
        FileOutputStream stream;
        try {
            File parent = new File(System.getProperty("user.dir")+"\\files");
            if(!parent.exists()){
                ServerRMI.text.append("\n Creating folder...");
                if (!parent.mkdirs()){
                    return -1;
                }
            }
            File targetFile = new File(System.getProperty("user.dir")+"\\files\\["+idea+"]"+filename);
            stream = new FileOutputStream(targetFile);
            stream.write(file);
            stream.flush();
            stream.close();
        }catch(Exception e) {
            ServerRMI.text.append("\n Error!!!\n"+e);
            return -1;
        }
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeQuery("INSERT INTO Files_Ideas VALUES("+idea+" , '["+idea+"]"+filename+"')");
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL EXCEPTION:\n" + e);
            return -1;
        }
        return 0;
    }

    public int getPercentage(String idea, int ID_User) throws RemoteException {
        int ID_idea;
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT * From Ideas WHERE Ideas.Title Like '" + idea + "'");
            if (queryresult.first()) {
                ID_idea = queryresult.getInt(1);
                statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                queryresult = statement.executeQuery("SELECT User_Ideas.Percentage FROM User_Ideas " +
                        "WHERE User_Ideas.ID_USER = "+ID_User+
                        " AND User_Ideas.ID_IDEA = "+ID_idea);
                if (queryresult.first()){
                    pool.releaseConnection(conn);
                    return  queryresult.getInt(1);
                }
            }
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            ServerRMI.text.append("\n SQL EXCEPTION:\n" + e);
            return -1;
        }
        return -1;

    }

    public ArrayList<String> updateBuyList(String idea) throws RemoteException{
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> aux;
        try{
            Connection conn = pool.getConnection() ;
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet queryresult = statement.executeQuery("SELECT * FROM WantBuy WHERE ID_Idea LIKE '" + idea + "' ORDER BY timestamp");
            while(queryresult.next()){
                aux = buyShares(idea, queryresult.getInt(4), queryresult.getInt(1), queryresult.getInt(3), queryresult.getInt(6),queryresult.getString(5));
                if (aux!=null){
                    result.addAll(aux);
                    statement.executeQuery("DELETE FROM WantBuy WHERE ID_Idea LIKE '" + idea + "' AND ID_buyer = "+queryresult.getInt(1)+" AND timestamp = to_DATE("+queryresult.getDate(5)+")");

                }
            }
            pool.releaseConnection(conn);
        }catch (SQLException e){
            ServerRMI.text.append("\n SQL exception:\n" + e);
        }
        return result;
    }

    public boolean addBuyList(String idea, int percentage, int ID_User, int sellingPrice, int maxprice) throws RemoteException{
        boolean result = false;
        try{
            Connection conn = pool.getConnection() ;
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeQuery("INSERT INTO WantBuy " +
                    "VALUES("+ID_User+" , '"+idea+"' , "+sellingPrice+
                    " , "+percentage+" , SYSTIMESTAMP , "+maxprice+")");
            pool.releaseConnection(conn);
            result = true;
        }catch (SQLException e){
            ServerRMI.text.append("\n SQL exception:\n" + e);
        }
        return result;
    }
}