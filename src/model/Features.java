package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuidos
 */

//TODO: Remover estes warnings!
@SuppressWarnings("UnusedDeclaration")
public interface Features extends Remote{
    public void CreateRMIServer() throws RemoteException;
    public void ConnectDatabase() throws RemoteException;

    /**
     * Method to Login the user
     *
     * @param user the username
     * @param pass the password
     * @return -1 if incorrect password
     *         0 if user is incorrect or do not exists
     *
     * @throws RemoteException
     */
    public int Login(String user, String pass) throws RemoteException;

    /**
     *  Method to Register a new User
     * @param user ArrayList with the User Information:
     *             - username
     *             - password
     *             - name
     *             - lastname
     *             - e-mail
     *
     * @return 1 in case of success
     *         -1 in case of error
     *         0 if the user already exists
     *
     * @throws RemoteException
     */
    public int newUser(ArrayList<String> user) throws RemoteException;

    /**
     * Function to add a new Idea
     *
     * @param data    Idea info
     *                <p>topicID (lots of them)</p>
     *                <p>size - 4 = IdeaName </p>
     *                <p>size - 3 = IdeaDescription</p>
     *                <p>size - 2 = DEIcoins   </p>
     *                <p>size - 1 = The ID of the parent Idea (-1 in case of original idea)</p>
     * @param id_user Id of the user
     * @param ischild If the idea is a reply
     * @return the IdeaId or -1 in case of error or -2 in case of not enough money
     * @throws RemoteException
     */
    public int newIdea(ArrayList<String> data, int id_user, boolean ischild) throws RemoteException;

    /**
     *
     * @param data a arrayList of strings containing only the Topic Title
     * @return the ID of the Topic or -1in case of error
     * @throws RemoteException
     */
    public int newTopics(String data) throws RemoteException;

    /**
     * Function to get all the topics of the database
     * @return An ArrayList<String> containig all the descriptions of the Topic
     * @throws RemoteException
     */
    public ArrayList<String> getTopics() throws RemoteException;

    /**
     * Return the Title of all the ideas Beloging to a Topic
     * @param Topic the name of a topic
     * @return ArrayList<String> containing the titles of all the ideas belonging to topic
     * @throws RemoteException
     */
    public ArrayList<String> getIdeas(String Topic) throws RemoteException;

    /**
     * Return the Idea info
     * @param idea the title of the idea
     * @return  ArrayList<String> containing the information of an idea
     * @throws RemoteException
     */
    public ArrayList<String> getIdeaDetails(String idea) throws RemoteException;

    /**
     *
     * @param idea the title of the idea
     * @return an arrayList with n times the sequence <username (a String,numberofshares (an Int),the selling price(a double)>
     * @throws RemoteException
     */
    public ArrayList<String> getSharesInfo(String idea)throws RemoteException;

    /**
     * Buys some shares of an idea
     * @param idea the title of the idea
     * @param percentage the number of shares
     * @param ID_User the id of the user
     * @param sellingPrice the sellingPrice of the bought shares
     * @param maxprice the max price to pay for all of the actions
     * @param timeStamp a time stamp (
     * @return an array List of the users involved in the transaction [the Id of the seller, a message (notification) and the money spent]
     * @throws RemoteException
     */
    public ArrayList<String> buyShares(String idea, int percentage, int ID_User, double sellingPrice, double maxprice, String timeStamp) throws RemoteException;

    public ArrayList<String> getTransactions(int ID_user,boolean limited) throws RemoteException;

    /**
     *
     * @param ID_User the user ID
     * @return 1 in case of success, -1 in case of error
     * @throws RemoteException
     */
    public int updateLastLogin(int ID_User) throws RemoteException ;

    public ArrayList<String> getuserIdeas(int ID_User) throws RemoteException;

    public ArrayList<String> getuserIdeasDetails (int ID_User, boolean all, String idea) throws RemoteException;

    /**
     * Method to Delete an Idea (doesn't actually remove from DB, only sets Deleted = true)
     * @param idea Title of the Idea
     * @param ID_User ID of the user
     * @return 1 in case of success
     *         -1 in case of error
     * @throws RemoteException
     */
    public int deleteIdea(String idea, int ID_User) throws RemoteException;

    /**
     * Method to Set a new Share Price
     * @param idea Idea
     * @param price New Price
     * @param ID_User User ID
     * @return cenas
     * @throws RemoteException
     */
    public ArrayList<String> setSharePrice(String idea,double price, int ID_User) throws RemoteException;

    /**
     * Return the number of coins of the user
     * @param ID_User
     * @return the number of DEICOINS (double)
     * @throws RemoteException
     */
    public double getCoins(int ID_User) throws RemoteException;

    /**
     *
     * @param idea
     * @param filename
     * @param file
     * @return
     * @throws RemoteException
     */
    public int saveFile(int idea, String filename, byte[] file) throws RemoteException;

    /**
     *
     * @param idea the title of the idea
     * @param ID_User the ID of the user
     * @return  the percentage or -1 in case of error
     * @throws RemoteException
     */
    public int getPercentage(String idea, int ID_User) throws RemoteException;

    /**
     * Method to Add an Idea to an User Watchlist
     * @param ID_User ID of the User
     * @param ID_idea ID of the Idea
     * @return -1 in case of error
     *         0  if the User already has the Idea in the Watchlist
     *         1  if the Idea was added with Success
     * @throws RemoteException
     */
    public int addToWatchList(int ID_User, int ID_idea) throws RemoteException;

    /**
     * Method to Remove an Idea from a User's Watchlist
     * @param ID_User ID of the User
     * @param ID_idea ID of the Idea
     * @return 1 in case of sucess
     *         -1 in case of error
     * @throws RemoteException
     */
    public int removeFromWatchList(int ID_User, int ID_idea) throws RemoteException;

    /**
     * To get the state of the idea (is being watch or not)
     * @param ID_User
     * @param ID_idea
     * @return -1 in case of error, 0 in case of not being watched, 1 if being watched
     * @throws RemoteException
     */
    public int isInWatchList(int ID_User, int ID_idea) throws RemoteException;

    /**
     * Method to Add an Idea to the Hall of Fame (can only be done by root)
     * @param ID_Idea ID of Idea
     * @return
     */
    public int addToHallOfFame(int ID_Idea) throws RemoteException;

    /**
     * Method to Search Topics in DB
     * @param topic to be search
     * @return ArrayList with correspondent topics
     * @throws RemoteException
     */
    public ArrayList<String> searchTopics(String topic) throws RemoteException;

    /**
     * Method to Search Ideas ( by Title )
     * @param idea Title of the idea
     * @return Array with ideas
     * @throws RemoteException
     */
    public ArrayList<String> searchIdeas(String idea) throws RemoteException;

    /**
     * GETS THE USER WATCHlIST
     * @param ID_User
     * @return the return is equal to the userIdea
     * @throws RemoteException
     */
    public ArrayList<String> viewWatchList(int ID_User) throws RemoteException;

    /**
     * to get the halloffame
     * @return the usual
     * @throws RemoteException
     */
    public ArrayList<String> viewHallOfFame() throws RemoteException;

    public int newFacebookUser(String ID_facebook, String userName) throws RemoteException;

    public int authenticateFacebook(String idFacebook, String username) throws RemoteException;

}