package action;

import model.NewClient;


/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuidos
 */
public class Register extends Action {

    public boolean inputError;
    public NewClient newClient;

    public Register() {
        this.newClient = new NewClient();
    }

    public String execute() {
        String answer = this.newClient.registerNewUSer();
        if(answer.equals("INPUT_ERROR"))
            this.inputError = true;

        return answer;
    }

    public void setUsername(String username) {
        newClient.setUsername(username);
    }

    public void setPassword(String password) {
        newClient.setPassword(password);
    }

    public void setEmail(String email) {
        newClient.setEmail(email);
    }

    public void setName(String name) {
        newClient.setName(name);
    }

    public void setLastName(String lastName) {
        newClient.setLastName(lastName);
    }

}
