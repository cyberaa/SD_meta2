package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

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
 * @project Sistemas Distribu�dos
 */
public class Login extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    public Features RMIServer = null;

    private String userName;
    private String password;
    private String message;
    private String messagePassword;

    public String execute() {
        setMessage("Hello " + userName);
        setMessagePassword("Your password is:" + password);
        String lol= hashPassword(password);
        try {
            if(RMIServer==null){
                System.getProperties().put("java.security.policy", "policy.all");
                try{
                    this.RMIServer = (Features) Naming.lookup("rmi://127.0.0.1:7000/IdeaBroker");
                }catch(Exception e){
                    System.err.println(e);
                    return "SUCCESS";}
            }
            RMIServer.Login(getUserName(),lol);
        } catch (Exception e) {
            return "ERROR";
        }
        System.out.println("I'M THE BOSS!!!!");
        return "SUCCESS";
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
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

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=password;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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
