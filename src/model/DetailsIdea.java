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
    private int numberShares;
    private int inWatchList;
    private boolean isRoot;
    private ArrayList<String> topics;
    private ArrayList<String> owners;

    public DetailsIdea(){
        topics = new ArrayList<String>();
        owners = new ArrayList<String>();
        inWatchList=-1;
    }

    public int getIdeaDetails(Features RMIServer,String ideaName, int UserID){
        ArrayList<String> aux;
        try {
            if(RMIServer!=null){
                aux = RMIServer.getIdeaDetails(ideaName);
                if(aux==null){
                    return -1;
                }else{
                    ideaID = aux.get(0);
                    title = aux.get(1);
                    description = aux.get(2);
                    date = aux.get(3);
                    initvalue = Double.parseDouble(aux.get(4));
                    ideaValue = Double.parseDouble(aux.get(5));
                    int numbertopics = Integer.parseInt(aux.get(aux.size()-2))+6;
                    for (int i=6;i<(numbertopics);i++){
                        topics.add(aux.get(i));
                    }
                    int numberowners = Integer.parseInt(aux.get(aux.size()-1))+numbertopics;
                    for(int i=numbertopics;i<numberowners;i++){
                        owners.add(aux.get(i));
                    }
                    int answer = RMIServer.isInWatchList(UserID,Integer.parseInt(aux.get(0)));
                    if (answer ==-1){
                        return -1;
                    }
                    inWatchList = answer;

                    answer = RMIServer.getPercentage(ideaName, UserID);
                    if (answer ==-1){
                        return -1;
                    }
                    numberShares = answer;

                    if (UserID==1){
                        isRoot=true;
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

    public int getInWatchList() {
        return inWatchList;
    }

    public void setInWatchList(int inWatchList) {
        this.inWatchList = inWatchList;
    }

    public boolean getRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public int getNumberShares() {
        return numberShares;
    }

    public void setNumberShares(int numberShares) {
        this.numberShares = numberShares;
    }
}
