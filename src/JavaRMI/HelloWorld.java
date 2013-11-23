package JavaRMI;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class HelloWorld {

    private String message;
    private String messagePassword;

    private String userName;
    private String password;

    public HelloWorld(){
    }

    public String execute() {
        setMessage("Hello " + getUserName());
        setMessagePassword("Your password is:"+getPassword());
        return "SUCCESS";
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

}