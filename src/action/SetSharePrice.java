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

    public String execute(){
        Features server = getRMIserver();
        int userID = getUserID();
        if (getIdeaTitle() == null){
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
            return "ERROR";
        }
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
}
