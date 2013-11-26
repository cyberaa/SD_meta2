package action;

import model.*;
import java.math.BigInteger;
import java.rmi.Naming;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class Login extends Action {

    public Features RMIServer = null;
    private boolean tried=false;

    private String message;
    private String messagePassword;


    public Login(){
        client = new Client();
    }
    public String execute() {
        //super.execute();
        setMessage("Hello " + client.getUserName());
        setMessagePassword("Your password is:" + client.getPassword());
        if(client.getPassword()==null){
            return "ERROR";
        }
        String hashedPass = hashPassword(client.getPassword());
        int answer;
        try {
            if(RMIServer==null){
                System.getProperties().put("java.security.policy", "policy.all");
                try{
                    this.RMIServer = (Features) Naming.lookup("rmi://127.0.0.1:7000/IdeaBroker");
                }catch(Exception e){
                    System.err.println(e);
                    return "ERROR";}
            }
            answer = RMIServer.Login(client.getUserName(),hashedPass);
        } catch (Exception e) {
            System.err.println(e);
            return "ERROR";
        }
        System.out.println("ANSWER!!!"+answer+"   pass:"+client.getPassword()+"   username"+client.getUserName());
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

    private String hashPassword(String pass)
    {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Cannot find hashing algorithm:\n" + e);
            System.exit(-1); //FIXME?
        }
        if(m == null)
        {
            System.out.println("Cannot find hashing algorithm.");
            System.exit(-1);
        }
        m.reset();
        m.update(pass.getBytes());

        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashText = bigInt.toString(16);

        while(hashText.length() < 32)
            hashText = "0" + hashText;

        return hashText;
    }

}
