package model;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class Transactions {
    private ArrayList<String> transactions;

    public Transactions(){
        transactions = new ArrayList<String>();
    }

    public int getIdeaTransactions(String IdeaTitle){
        return -666;
    }

    public int getUserTransactions(Features RMIServer, int UserID){
        try{
            if (RMIServer!=null){
                transactions = RMIServer.getTransactions(UserID,false);
                if (transactions==null){
                    return -1;
                }
            }else{
                return -666;
            }
        }catch (Exception e){
            System.out.println("Error getting the transactions"+e);
        }
        return 1;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<String> transactions) {
        this.transactions = transactions;
    }
}
