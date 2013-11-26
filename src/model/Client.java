package model;

import java.math.BigInteger;
import java.rmi.Naming;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class Client {
    private String userName;
    private String password;
    private Features RMIServer = null;
    private int number_test;

    public String getUserName() {
        return userName;
    }
    public Features getRMIServer(){
        number_test=0;
        if(this.RMIServer==null){
            System.getProperties().put("java.security.policy", "policy.all");
            try{
                this.RMIServer = (Features) Naming.lookup("rmi://127.0.0.1:7000/IdeaBroker");
                return RMIServer;
            }catch(Exception e){
                System.err.println(e);
                if(number_test<5)
                return getRMIServer();
            }
        }else{
            return RMIServer;
        }
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=password;
    }
    public void setUserName(String userName) {
        this.userName = hashPassword(userName);
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
