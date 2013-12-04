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
public class Search extends Action {
    private String search;
    private Ideas IdeasList;

    public String execute(){
        Features server = getRMIserver();
        int userID = getUserID();
        IdeasList = new Ideas();
        if(search==null)
            return "ERROR";
        if(IdeasList.getIdeas(server ,getUserID(), search)==null){
            server = getRMIserver();
            if(IdeasList.getIdeas(server ,getUserID(), search)==null){
                return "RMIERROR";
            }
        }
        System.out.println(getIdeasList());
        return "SUCCESS";
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String text) {
        this.search = text;
    }

    public ArrayList<DetailsIdea> getIdeasList() {
        return IdeasList.getIdeas();
    }

    public void setIdeasList(ArrayList<DetailsIdea> ideasList) {
        IdeasList.setIdeas(ideasList);
    }
}
