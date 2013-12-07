package action;

import model.DetailsIdea;
import model.Features;
import model.Ideas;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribu�dos
 */
public class ViewWatchList extends Action {
    private Ideas IdeasList;

    public String execute(){
        Features server = getRMIserver();

        IdeasList = new Ideas();
        if(IdeasList.getWatchList(server, getUserID())==null){
            server = getRMIserver();
            if(IdeasList.getWatchList(server, getUserID())==null){
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
