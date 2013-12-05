package action;

import model.DetailsTopic;
import model.Features;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class ViewTopic extends Action {
    private String topic;
    private DetailsTopic topics;
    public String execute(){
        Features server = getRMIserver();
        topics = new DetailsTopic();
        if(topics.getTopicIdeas(server ,topic)==-666){
            server = getRMIserver();
            if(topics.getTopicIdeas(server ,topic)==-666){
                return "RMIERROR";
            }
        }
        return "SUCCESS";
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public DetailsTopic getTopics() {
        return topics;
    }

    public void setTopics(DetailsTopic topics) {
        this.topics = topics;
    }
    public ArrayList<String> getTopicIdeas(){
        return this.topics.getIdeas();
    }
}
