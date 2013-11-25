package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Client;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class Action extends ActionSupport implements SessionAware {

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
            this.client = new Client();
            session.put("client", client);
        } else
            this.client = (Client) session.get("client");
    }

    public boolean getClientStatus(){
        if (!session.containsKey("client")) {
            return false;
        }
        return true;
    }

    public String execute()
    {
        getClientSession();
        return "SUCCESS";
    }
}
