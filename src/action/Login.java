package action;

import model.*;

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
    public Login(){
        client = new Client();
        login = new LoginBean();
    }
    public String execute() {
        int answer = login.login(getRMIserver());
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
}


