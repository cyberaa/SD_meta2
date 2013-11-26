package model;

import java.rmi.Naming;
import java.util.ArrayList;

public class NewClient {

    private String username;
    private String password;
    private String email;
    private String name;
    private String lastName;

    private boolean reconnect = false;
    private Features RMIServer = null;

    public NewClient() {

    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<String> getNewUserElements() {

        ArrayList<String> newUserMsg = new ArrayList<String>();
        newUserMsg.add(this.getUsername());
        //TODO HASH PASSWORD
        newUserMsg.add(this.getPassword());
        newUserMsg.add(this.getName());
        newUserMsg.add(this.getLastName());
        newUserMsg.add(this.getEmail());

        return newUserMsg;
    }

    public void registerNewUSer() {

    }
}
