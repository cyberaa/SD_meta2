package model;

import java.math.BigInteger;
import java.rmi.Naming;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuidos
 */
public class NewClient {

    private String username;
    private String password;
    private String email;
    private String name;
    private String lastName;

    private boolean reconnect = false;
    private Features RMIServer = null;

    //TODO isto é preciso?
    public NewClient() {}

    /**
     * Method to connect to RMI Server
     * @return
     */
    public Features getRMIServer(){
        if(this.RMIServer == null || reconnect == false) {
            System.getProperties().put("java.security.policy", "policy.all");
            try {
                this.RMIServer = (Features) Naming.lookup("rmi://127.0.0.1:7000/IdeaBroker");
            } catch(Exception e){
                System.err.println(e);
                RMIServer = null;
            }
        }
        return RMIServer;
    }

    /**
     * Method to set username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method to Set Password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to Set Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to Set Last Name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method to Set E-mail
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to Get Password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to get Name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to Get Last Name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method to Get E-mail
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to Hash a Password using MD5 Algorithm
     * @param pass password to be hashed
     * @return hashed password
     */
    private String hashPassword(String pass)    {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Cannot find hashing algorithm:\n" + e);
            //FIXME?
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

    /**
     * Method to check if all fields are correctly completed
     * @return true, if all fields correctly completed, false if at least one is wrong.
     */
    private boolean fieldsCompleted() {
        int numberFieldsCompleted = 0;
        if(this.getName()!= null || !this.getName().contains(" ") )
            numberFieldsCompleted++;
        if(this.getLastName() != null && !this.getLastName().contains(" "))
            numberFieldsCompleted++;
        if(this.getEmail() != null && !this.getEmail().contains(" ") &&
                this.getEmail().contains("@"))
            numberFieldsCompleted++;
        if(this.getUsername() != null && !this.getUsername().contains(" "))
            numberFieldsCompleted++;
        if(this.getPassword() != null && !this.getPassword().contains(" "))
            numberFieldsCompleted++;
        if(numberFieldsCompleted == 5)
            return true;
        else
            return false;
    }

    /**
     * Method to get the Register User Elements:
     *  - username
     *  - password
     *  - name
     *  - lastname
     *  - e-mail
     * @return ArrayList with all the elements in correct order
     */
    public ArrayList<String> getNewUserElements() {
        ArrayList<String> newUserMsg = new ArrayList<String>();
        newUserMsg.add(this.getUsername());
        newUserMsg.add(hashPassword(this.getPassword()));
        newUserMsg.add(this.getName());
        newUserMsg.add(this.getLastName());
        newUserMsg.add(this.getEmail());
        return newUserMsg;
    }

    /**
     * Method to Register a new User in the system
     * @return SUCCESS if the user was successfully registered
     *         RMIERROR in case of connection error or exception in RMI Server
     */
    public String registerNewUSer() {
        int answer;
        if(!fieldsCompleted())
            return "INPUT_ERROR";
        try {
            Features rmi;
            rmi = this.getRMIServer();
            if(rmi!=null) {
                answer = rmi.newUser(this.getNewUserElements());
            }
            else{
                return "RMIERROR";
            }
        } catch (Exception e) {
            System.out.println("Exception registerNewUser - RMI");
                return "RMIERROR";
         }

        return "SUCCESS";
    }

}
