package model;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribu�dos
 */
public class DetailsTopic {
    private ArrayList<String> ideas;

    public int getTopicIdeas(Features RMIServer,String Topic){
        try {
            if(RMIServer!=null){
                ideas = RMIServer.getIdeas(Topic);
                if(ideas==null){
                    return -1;
                }
            }else{
                return -666;
            }
        } catch (Exception e) {
            System.out.println("ERRO getTopics"+e);
            return -666;
        }
        return 1;
    }
    public ArrayList<String> getIdeas() {
        return ideas;
    }

    public void setIdeas(ArrayList<String> Ideas) {
        ideas = Ideas;
    }
}
