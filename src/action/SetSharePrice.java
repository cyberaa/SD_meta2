package action;

import model.Features;
import model.Ideas;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class SetSharePrice extends Action {

    private double SharesPrice;
    private String IdeaTitle;
    private Ideas ideas;
    private boolean retry;
    private char SetResult;

    public String execute(){
        Features server = getRMIserver();
        int userID = getUserID();
        if (getIdeaTitle() == null){
            SetResult = '1';
            return "ERROR";
        }
        ideas = new Ideas();
        int answer = ideas.setSharePrice(server, getIdeaTitle(),userID, SharesPrice);
        if (answer==-666){
            if (retry==false){
                retry=true;
                execute();
            }
            return "RMIERROR";
        }else if(answer == -1){
            SetResult = '1';
            return "ERROR";
        }
        SetResult = '2';
        return "SUCCESS";
    }

    public double getSharesPrice() {
        return SharesPrice;
    }

    public void setSharesPrice(double sharesPrice) {
        SharesPrice = sharesPrice;
    }

    public String getIdeaTitle() {
        return IdeaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        IdeaTitle = ideaTitle;
    }

    public char getSetResult() {
        return SetResult;
    }

    public void setSetResult(char setResult) {
        SetResult = setResult;
    }
}
