package action;

import model.DetailsIdea;
import model.Features;
import model.Ideas;
import model.Topics;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class Search extends Action {
    private String search;
    private String SearchOption;

    private Ideas IdeasList;
    private Topics TopicsList;

    public String execute(){
        Features server = getRMIserver();
        if(search==null)
            return "ERROR";
        if(SearchOption.equals("Ideas")){
            IdeasList = new Ideas();
            if(IdeasList.getIdeas(server ,getUserID(), search)==null){
                server = getRMIserver();
                if(IdeasList.getIdeas(server ,getUserID(), search)==null){
                    return "RMIERROR";
                }
            }
            return "SUCCESSIDEAS";
        }else{
            setTopicsList(new Topics());
            if(TopicsList.searchTopics(server ,getUserID(), search)==null){
                server = getRMIserver();
                if(TopicsList.searchTopics(server ,getUserID(), search)==null){
                    return "RMIERROR";
                }
            }
            return "SUCCESSTOPICS";
        }
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

    public String getSearchOption() {
        return SearchOption;
    }

    public void setSearchOption(String searchOption) {
        SearchOption = searchOption;
    }

    public ArrayList<String> getTopicsList() {
        return TopicsList.getTopics();
    }

    public void setTopicsList(Topics topicsList) {
        TopicsList = topicsList;
    }
}
