package model;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 */
public class NewIdea {

    private String titleIdea;
    private String descriptionIdea;
    private double deiCoinsIdea;
    private String topicsList;

    /**
     * Method to set Idea Title
     * @param titleIdea
     */
    public void setTitleIdea(String titleIdea) {
        this.titleIdea = titleIdea;
    }

    /**
     * Method to set Idea's Description
     * @param descriptionIdea
     */
    public void setDescriptionIdea(String descriptionIdea) {
        this.descriptionIdea = descriptionIdea;
    }

    /**
     * Method to set price per share of an idea
     * @param deiCoinsIdea
     */
    public void setDeiCoinsIdea(double deiCoinsIdea) {
        this.deiCoinsIdea = deiCoinsIdea;
    }

    public void setTopicsList(String topicsList) {
        this.topicsList = topicsList;
    }

    /**
     * Method to get Idea's Title
     * @return title idea
     */
    public String getTitleIdea() {
        return titleIdea;
    }

    /**
     * Method to get Idea's Description
     * @return description
     */
    public String getDescriptionIdea() {
        return descriptionIdea;
    }

    /**
     * Method to get price per share of an idea
     * @return price
     */
    public double getDeiCoinsIdea() {
        return deiCoinsIdea;
    }

    /**
     * Method to get the String of Topics ( yet to be parsed )
     * @return
     */
    public String getTopicsList() {
        return topicsList;
    }


    public ArrayList<String> getNewIdeaElements() {
        ArrayList<String> newIdea = new ArrayList<String>();

        newIdea.add(this.getTopicsList());
        newIdea.add(this.getTitleIdea());
        newIdea.add(this.getDescriptionIdea());
        newIdea.add(Double.toString(this.getDeiCoinsIdea()));

        return newIdea;
    }


    public void submitNewIdea() {
         System.out.println(getNewIdeaElements());
    }

}
