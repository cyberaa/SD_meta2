package action;

import model.Transactions;
import model.Features;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class ViewTransactions extends Action {
    private Transactions transac;
    public String execute(){
        Features server = getRMIserver();
        int UserID = getUserID();
        transac = new Transactions();
        if(transac.getUserTransactions(server, UserID)==-666){
            server = getRMIserver();
            if(transac.getUserTransactions(server, UserID)==-666){
                return "RMIERROR";
            }
        }
        return "SUCCESS";
    }
    public ArrayList<String> getTransac(){
        return transac.getTransactions();
    }
}
