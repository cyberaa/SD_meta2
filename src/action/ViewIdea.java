package action;

import model.DetailsIdea;
import model.Features;


/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribu�dos
 */
public class ViewIdea extends Action {
    private String idea;
    private DetailsIdea ideaDet;
    private char BuyResult;
    private char SetResult;
    private char DeleteResult;
    private char watchListResult;
    public ViewIdea(){
        BuyResult = 0;
        SetResult = 0;
        DeleteResult = 0;
    }
    public String execute(){
        Features server = getRMIserver();
        ideaDet = new DetailsIdea();
        int answer = ideaDet.getIdeaDetails(server, getIdea(),getUserID());
        if(answer==-666){
            server = getRMIserver();
            answer = ideaDet.getIdeaDetails(server, getIdea(),getUserID());
            if(answer==-666){
                return "RMIERROR";
            }
        }
        return "SUCCESS";
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public DetailsIdea getIdeaDet() {
        return ideaDet;
    }

    public void setIdeaDet(DetailsIdea ideaManager) {
        this.ideaDet = ideaManager;
    }

    public char getDeleteResult() {
        return DeleteResult;
    }

    public void setDeleteResult(char deleteResult) {
        DeleteResult = deleteResult;
    }

    public char getSetResult() {
        return SetResult;
    }

    public void setSetResult(char setResult) {
        SetResult = setResult;
    }

    public char getBuyResult() {
        return BuyResult;
    }

    public void setBuyResult(char buyresult) {
        BuyResult = buyresult;
    }

    public char getWatchListResult() {
        return watchListResult;
    }

    public void setWatchListResult(char watchListResult) {
        this.watchListResult = watchListResult;
    }
}
