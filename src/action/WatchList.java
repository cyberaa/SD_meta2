package action;

import model.Features;
import model.Ideas;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class WatchList extends Action {
    private char watchListResult;
    private String IdeaID;
    private String IdeaTitle;
    private Ideas ideas;
    private int inWatchList;

    public String execute(){
        Features server = getRMIserver();
        int userID = getUserID();
        if (IdeaID == null){
            watchListResult = '1';
        }
        ideas = new Ideas();
        if (inWatchList==1){
            int answer = ideas.removeFromWatchListStatus(server,IdeaID,userID);
            if (answer==-666){
                server = getRMIserver();
                answer = ideas.removeFromWatchListStatus(server,IdeaID,userID);
                if(answer==-666){
                    return "RMIERROR";
                }
            }
            if(answer == -1){
                watchListResult = '1';
                return "ERROR";
            }
            watchListResult = '2';
            return "SUCCESS";
        }
        else{
            int answer = ideas.addToWatchListStatus(server,IdeaID,userID);
            if (answer==-666){
                server = getRMIserver();
                answer = ideas.addToWatchListStatus(server,IdeaID,userID);
                if(answer==-666){
                    return "RMIERROR";
                }
            }
            if(answer == -1){
                watchListResult = '1';
                return "ERROR";
            }
        }
        watchListResult = '3';
        return "SUCCESS";
    }

    public void setIdeaID(String ideaID) {
        IdeaID = ideaID;
    }

    public void setInWatchList(int inWatchList) {
        this.inWatchList = inWatchList;
    }

    public char getWatchListResult() {
        return watchListResult;
    }

    public void setWatchListResult(char watchListResult) {
        this.watchListResult = watchListResult;
    }

    public String getIdeaID() {
        return IdeaID;
    }

    public String getIdeaTitle() {
        return IdeaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        IdeaTitle = ideaTitle;
    }
}
