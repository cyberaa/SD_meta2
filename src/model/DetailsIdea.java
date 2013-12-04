package model;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class DetailsIdea {
    private String ideaID;
    private String title;
    private String description;
    private Double ideaValue;
    private ArrayList<String> topics;

    public DetailsIdea(){
        topics = new ArrayList<String>();
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getIdeaValue() {
        return ideaValue;
    }

    public void setIdeaValue(Double ideaValue) {
        this.ideaValue = ideaValue;
    }

    public ArrayList<String> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<String> topics) {
        topics = topics;
    }
    public void addTopics(String tps) {
        this.topics.add(tps);
    }

    public String getIdeaID() {
        return ideaID;
    }

    public void setIdeaID(String ideaID) {
        this.ideaID = ideaID;
    }
}
