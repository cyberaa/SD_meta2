package action;

import model.NewIdea;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * Distributed Systems
 */
public class SubmitIdea extends Action {

    public NewIdea newIdea;

    public SubmitIdea() {
        this.newIdea = new NewIdea();
    }

    public String execute() {
        this.newIdea.submitNewIdea();

        return "SUCCESS";
    }

}
