package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class LoginBean {
    private String userName;
    private String password;
    public int login(Features RMIServer){
        int answer = 0;
        try {
            if(RMIServer!=null){
                answer = RMIServer.Login(userName,password);
            }else{
                answer=-666;
            }
        } catch (Exception e) {
            System.out.println(e);
            answer=-666;
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
    public String getUserName() {
        return userName;
    }
    private String hashPassword(String pass)    {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Cannot find hashing algorithm:\n" + e);
            System.exit(-1);
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
