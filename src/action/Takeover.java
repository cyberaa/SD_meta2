package action;

import model.Features;
import model.Ideas;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class Takeover extends Action{
    private char TakeOverResult;
    private Ideas ideas;
    private String IdeaTitle;
    public String execute(){
        Features server = getRMIserver();
        int userID = getUserID();
        if (getIdeaTitle() == null){
            TakeOverResult = '1';
            return "ERROR";
        }
        ideas = new Ideas();
        int answer = ideas.takeOver(server, userID, IdeaTitle);
        if (answer==-666){
            server = getRMIserver();
            answer = ideas.takeOver(server ,userID,IdeaTitle);
            if (answer==-666){
                return "RMIERROR";
            }
        }
        //in case of error
        if(answer < 1){
            TakeOverResult = '1';
        }
        //Added to the queue
        else if(answer == 1 ){
            TakeOverResult = '2';
        }
        return "SUCCESS";
    }
    public String getIdeaTitle() {
        return IdeaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        IdeaTitle = ideaTitle;
    }

    public char getTakeOverResult() {
        return TakeOverResult;
    }

    public void setTakeOverResult(char takeOverResult) {
        TakeOverResult = takeOverResult;
    }
}
