package action;

import model.Features;
import model.Ideas;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class BuyIdea extends Action{
    private char BuyResult;
    private Ideas ideas;
    private String SellingPrice;
    private String buyPrice;
    private String numberShares;
    private String IdeaTitle;
    public String execute(){
        Features server = getRMIserver();
        int userID = getUserID();
        if (getIdeaTitle() == null){
            BuyResult = '1';
            return "ERROR";
        }
        ideas = new Ideas();
        int answer = ideas.buyShares(server,IdeaTitle, getUserID(),numberShares,buyPrice,SellingPrice);
        if (answer==-666){
            server = getRMIserver();
            answer = ideas.buyShares(server,IdeaTitle, getUserID(),numberShares,buyPrice,SellingPrice);
            if (answer==-666){
                return "RMIERROR";
            }
        }
        getDEICoins();
        //in case of error
        if(answer == -1){
            BuyResult = '1';
        }
        //Added to the queue
        else if(answer == 0 ){
            BuyResult = '2';
        }else{
            BuyResult = '3';
        }
        return "SUCCESS";
    }

    public String getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        SellingPrice = sellingPrice;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getNumberShares() {
        return numberShares;
    }

    public void setNumberShares(String numberShares) {
        this.numberShares = numberShares;
    }

    public String getIdeaTitle() {
        return IdeaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        IdeaTitle = ideaTitle;
    }

    public char getBuyResult() {
        return BuyResult;
    }

    public void setBuyResult(char buyresult) {
        BuyResult = buyresult;
    }
}
