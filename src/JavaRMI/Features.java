import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuidos
 */

@SuppressWarnings("UnusedDeclaration")
public interface Features extends Remote{
    public void CreateRMIServer() throws RemoteException;
    public void ConnectDatabase() throws RemoteException;
    public int Login(String user, String pass) throws RemoteException;
    public int newUser(ArrayList<String> user) throws RemoteException;
    public int newIdea(ArrayList<String> data, int id_user, boolean ischild) throws RemoteException;
    public int newTopics(ArrayList<String> data) throws RemoteException;
    public ArrayList<String> getTopics() throws RemoteException;
    public ArrayList<String> getIdeas(String Topic) throws RemoteException;
    public ArrayList<String> getIdeaDetails(String idea) throws RemoteException;
    public ArrayList<String> getSharesInfo(String idea)throws RemoteException;
    public ArrayList<String> buyShares(String idea, int percentage, int ID_User, int sellingPrice, int maxprice, String timeStamp) throws RemoteException;
    public ArrayList<String> getTransactions(int ID_user,boolean limited) throws RemoteException;
    public int updateLastLogin(int ID_User) throws RemoteException ;
    public ArrayList<String> getuserIdeas(int ID_User) throws RemoteException;
    public int deleteIdeas(String idea, int ID_User) throws RemoteException;
    public ArrayList<String> setSharePrice(String idea,int price, int ID_User) throws RemoteException;
    public int getCoins(int ID_User) throws RemoteException;
    public int saveFile(int idea, String filename, byte[] file) throws RemoteException;
    public int getPercentage(String idea, int ID_User) throws RemoteException;
}
