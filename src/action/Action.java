package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Client;
import model.Features;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribu�dos
 */
public class Action extends ActionSupport implements SessionAware {
    protected static final long serialVersionUID = 4L;
    protected Map<String, Object> session;
    protected Client client;

    /**
     * Needed by SessionAware
     *
     * @param session Used by struts to set the session
     */
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    /**
     * Synchronizes this.client and the current session, either creating it if session doesn't have it,
     * or loading it from the session if it exists.
     */
    public void getClientSession() {
        if (!session.containsKey("client")) {
            if (client==null){
                this.client = new Client();
            }
            session.put("client", client);
        }else
            this.client = (Client) session.get("client");
    }

    public void updateClientSession(){
        System.out.println(client.getUserID());
        System.out.println(client.getUserName());
        if (session.containsKey("client")) {
            session.remove("client");
        }
            session.put("client", client);
    }

    public String getUserName() {
        return client.getUserName();
    }

    public void setUser(Client user){

    }

    public void setUserNametoBean(String userName) {
        client.setUserName(userName);
        updateClientSession();
    }
    public Features getRMIserver(){
        getClientSession();
        return client.getRMIServer();
    }

    public void setUserID(int userID) {
        System.out.println(userID);
        client.setUserID(userID);
        updateClientSession();
    }

    public int getUserID() {
        return client.getUserID();
    }

    public void setDEICoins(double coins){
        client.setDEICoins(coins);
        updateClientSession();
    }
    public double getDEICoins(){
        return client.getDEICoins();
    }
}
