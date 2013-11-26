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

    private String message;
    private String messagePassword;
    private boolean retry;

    public Login(){
        client = new Client();
    }
    public String execute() {
        //super.execute();
        setMessage("Hello " + client.getUserName());
        setMessagePassword("Your password is:" + client.getPassword());
        String password = client.getPassword();
        int answer = 0;
        try {
            Features rmi;
            rmi = client.getRMIServer();
            if(rmi!=null){
                answer = rmi.Login(client.getUserName(),password);
            }else{
                return "RMIERROR";
            }
        } catch (Exception e) {
            if (retry ==false ){
                retry=true;
                client.setReconnect(true);
                return this.execute();
            }else{
                retry=false;
                System.err.println(e);
                return "RMIERROR";
            }

        }
        System.out.println(answer);
        if(answer<1){
            tried=true;
            return "RETRY";
        }else{
            return "SUCCESS";
        }
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessagePassword() {
        return messagePassword;
    }
    public void setMessagePassword(String message) {
        this.messagePassword = message;
    }
    public boolean getTried() {
        return tried;
    }

}


