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
    public boolean submitted;
    public boolean error;
    private String errorMessage;
    public SubmitIdea() {
        this.newIdea = new NewIdea();
    }

    public String execute() {
        getClientSession();
        int result = this.newIdea.submitNewIdea(getRMIserver(),getUserID());
        if(result==-666){
            return "RMIERROR";
        }
        else if(result>0){
            submitted = true;
            return "SUCCESS";
        }
        error=true;
        errorMessage = "Vai-te Foder puta do caralho, para de tentar foder o site";
        return "INPUT_ERROR";
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
