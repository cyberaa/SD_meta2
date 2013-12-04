package action;

import model.*;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class Login extends Action {

    private boolean tried=false;
    private boolean rmierror=false;
    private LoginBean login;
    private boolean reconnect = false;
    private ArrayList<DetailsIdea> IdeasList;
    public Login(){
        login = new LoginBean();
    }
    public String execute() {
        getClientSession();
        int answer = login.login(getRMIserver());
        setUserID(answer);
        client.setDEICoins(client.getDEICoins());
        IdeasList = (new Ideas()).getIdeas(getRMIserver(),answer,null);
        if (answer ==  -666){
            if (!reconnect){
                reconnect=true;
                execute();
            }
            reconnect=false;
            //in case of failure to reconnect to the RMI or in case of SQL exception
            rmierror=true;
            return "RMIERROR";
        }
        if(answer<1){
            tried=true;
            return "RETRY";
        }else{
            setUserID(answer);
            setUserNametoBean(login.getUserName());
            getClientSession();
            return "SUCCESS";
        }
    }

    public boolean getTried() {
        return tried;
    }
    public boolean getRmierror(){
        return rmierror;
    }


   public void setPassword(String pass){
        login.setPassword(pass);
   }
    public void setUserName(String username){
        login.setUserName(username);
    }

    public ArrayList<DetailsIdea> getIdeasList() {
        return IdeasList;
    }

    public void setIdeasList(ArrayList<DetailsIdea> ideasList) {
        IdeasList = ideasList;
    }
}


