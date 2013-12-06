package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
    private String date;
    private Double ideaValue;
    private String ideaValueString;
    private Double initvalue;
    private String initValueString;
    private ArrayList<String> topics;
    private ArrayList<String> owners;

    public DetailsIdea(){
        topics = new ArrayList<String>();
        owners = new ArrayList<String>();
    }

    public int getIdeaDetails(Features RMIServer,String ideaName){
        ArrayList<String> aux;
        try {
            if(RMIServer!=null){
                aux = RMIServer.getIdeaDetails(ideaName);
                if(aux==null){
                    return -1;
                }else{
                    ideaID = aux.get(0);
                    title = aux.get(0);
                    description =aux.get(1);
                    date = aux.get(2);
                    initvalue = Double.parseDouble(aux.get(3));
                    ideaValue = Double.parseDouble(aux.get(4));
                    int numbertopics = Integer.parseInt(aux.get(aux.size()-2))+5;
                    for (int i=5;i<(numbertopics);i++){
                        topics.add(aux.get(i));
                    }
                    int numberowners = Integer.parseInt(aux.get(aux.size()-1))+numbertopics;
                    for(int i=numbertopics;i<numberowners;i++){
                        owners.add(aux.get(i));
                    }
                }
            }else{
                return -666;
            }
        } catch (Exception e) {
            System.out.println("ERRO getIdeaDetail"+e);
            return -666;
        }
        BigDecimal big = new BigDecimal(ideaValue);
        this.ideaValueString = new DecimalFormat("#0.0000").format(big);
        big = new BigDecimal(initvalue);
        this.initValueString = new DecimalFormat("#0.0000").format(big);
        return 1;
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

    public Double getInitvalue() {
        return initvalue;
    }

    public void setInitvalue(Double initvalue) {
        this.initvalue = initvalue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getOwners() {
        return owners;
    }

    public void setOwners(ArrayList<String> owners) {
        this.owners = owners;
    }

    public String getIdeaValueString() {
        return ideaValueString;
    }

    public void setIdeaValueString(String ideaValueString) {
        this.ideaValueString = ideaValueString;
    }

    public String getInitValueString() {
        return initValueString;
    }

    public void setInitValueString(String initValueString) {
        this.initValueString = initValueString;
    }
}
