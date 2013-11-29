package action;

import model.*;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribu�dos
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

    public String getUserName() {
        return client.getUserName();
    }

    public String getPassword() {
        return  login.getPassword();
    }
    public void setPassword(String password) {
        login.setPassword(password);
    }
    public void setUserName(String userName) {
        login.setUserName(userName);
    }

}


