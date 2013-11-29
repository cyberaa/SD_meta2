package model;

import java.util.ArrayList;
import java.util.StringTokenizer;

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


    public ArrayList<String> getTopicsId(Features RMIServer){
        StringTokenizer st = new StringTokenizer(this.topicsList," #");
        ArrayList<String> topicName = new ArrayList<String>();
        int topicID;
        while (st.hasMoreTokens()) {
            try{
                topicID = RMIServer.newTopics(st.nextToken());
                topicName.add(Integer.toString(topicID));
            }catch(Exception e){
                System.out.println(e);
                return null;
            }
        }
        return topicName;
    }


    public int submitNewIdea(Features RMIServer,int userID) {
        int answer = 0;
        try {
            if(RMIServer!=null){
                ArrayList<String> data = getTopicsId(RMIServer);
                if (data==null){
                    System.out.println("lolada");
                    answer=-666;
                }
                data.add(this.getTitleIdea());
                data.add(this.getDescriptionIdea());
                data.add(Double.toString(this.getDeiCoinsIdea()));
                //compatibility with the reply;
                data.add("0");
                answer = RMIServer.newIdea(data,userID,false);
            }else{
                answer=-666;
                System.out.println("ahahahah");
            }
        } catch (Exception e) {
            System.out.println(e);
            answer=-666;
        }
        return answer;
    }

}
