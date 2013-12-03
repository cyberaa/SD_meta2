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
    private int userID;
    private double DEICoins;
    private Features RMIServer = null;
    private boolean reconnect=false;

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
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public double getDEICoins() {
        return DEICoins;
    }

    public void setDEICoins(double DEICoins) {
        this.DEICoins = DEICoins;
    }
}
