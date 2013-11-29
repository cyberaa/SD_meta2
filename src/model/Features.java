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
     *                <p>size - 1 = The ID of the parent Idea </p>
     * @param id_user Id of the user
     * @param ischild If the idea is a reply
     * @return the IdeaId or -1 in case of error
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

    public ArrayList<String> getTopics() throws RemoteException;
    public ArrayList<String> getIdeas(String Topic) throws RemoteException;
    public ArrayList<String> getIdeaDetails(String idea) throws RemoteException;
    public ArrayList<String> getSharesInfo(String idea)throws RemoteException;
    public ArrayList<String> buyShares(String idea, int percentage, int ID_User, int sellingPrice, int maxprice, String timeStamp) throws RemoteException;
    public ArrayList<String> getTransactions(int ID_user,boolean limited) throws RemoteException;
    /**
     * UPDATES THE LAST LOGIN OF A USER
     * @param ID_User
     * @return 1 in case of sucess, -1 in case of failure
     * @throws RemoteException
     */
    public int updateLastLogin(int ID_User) throws RemoteException ;
    public ArrayList<String> getuserIdeas(int ID_User) throws RemoteException;
    public int deleteIdeas(String idea, int ID_User) throws RemoteException;
    public ArrayList<String> setSharePrice(String idea,int price, int ID_User) throws RemoteException;
    public int getCoins(int ID_User) throws RemoteException;
    public int saveFile(int idea, String filename, byte[] file) throws RemoteException;
    public int getPercentage(String idea, int ID_User) throws RemoteException;
}
