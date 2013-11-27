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

    private boolean retry;

    public Login(){
        client = new Client();
    }
    public String execute() {
        int answer = client.login();
        System.out.println(answer);
        if (answer ==  -666){
            //in case of failure to reconnect to the RMI or in case of SQL exception
            rmierror=true;
            return "RMIERROR";
        }
        if(answer<1){
            tried=true;
            return "RETRY";
        }else{
            return "SUCCESS";
        }
    }

    public boolean getTried() {
        return tried;
    }
    public boolean getRmierror(){
        return rmierror;
    }

}


