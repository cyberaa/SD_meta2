package action;

import model.DetailsIdea;
import model.Features;
import model.Ideas;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class ViewHallOfFame extends Action {
    private Ideas IdeasList;

    public String execute(){
        Features server = getRMIserver();

        IdeasList = new Ideas();
        if(IdeasList.getHallOfFame(server)==null){
            server = getRMIserver();
            if(IdeasList.getHallOfFame(server)==null){
                return "RMIERROR";
            }
        }
        return "SUCCESS";
    }

    public ArrayList<DetailsIdea> getIdeasList() {
        return IdeasList.getIdeas();
    }

    public void setIdeasList(ArrayList<DetailsIdea> ideasList) {
        IdeasList.setIdeas(ideasList);
    }
}
