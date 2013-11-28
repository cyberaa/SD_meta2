package model;

import java.math.BigInteger;
import java.rmi.Naming;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribu�dos
 */
public class Client {
    private String userName;
    private String password;
    private Features RMIServer = null;
    private boolean reconnect=false;

    public String getUserName() {
        return userName;
    }
    public Features getRMIServer(){
        if(this.RMIServer==null||this.reconnect==false){
            System.getProperties().put("java.security.policy", "policy.all");
            try{
                this.RMIServer = (Features) Naming.lookup("rmi://127.0.0.1:7000/IdeaBroker");
            }catch(Exception e){
                System.err.println(e);
                this.RMIServer = null;
            }
        }
        return this.RMIServer;
    }

    public int login(){
        int answer = 0;
        try {
            this.RMIServer = getRMIServer();
            if(this.RMIServer!=null){
                answer = this.RMIServer.Login(getUserName(),password);
            }else{
                answer=-666;
            }
        } catch (Exception e) {
            if (reconnect ==false ){
                reconnect=true;
                return login();
            }else{
                reconnect = false;
                answer=-666;
            }
        }
        return answer;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=hashPassword(password);
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    private String hashPassword(String pass)    {
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
            return null;
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
