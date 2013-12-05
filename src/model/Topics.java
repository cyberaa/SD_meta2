package model;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class Topics {
    private ArrayList<String> topics;
    public ArrayList<String> searchTopics(Features RMIServer,int userID,String topicTitle) {
        try {
            if(RMIServer!=null){
                topics = RMIServer.searchTopics(topicTitle);
                if(topics==null){
                    return null;
                }
            }else{
                return null;
            }
        } catch (Exception e) {
            System.out.println("ERRO getTopics"+e);
        }
        return topics;
    }

    public void setTopics(ArrayList<String> tpc) {
        this.topics = tpc;
    }

    public ArrayList<String> getTopics() {
        return topics;
    }
}
