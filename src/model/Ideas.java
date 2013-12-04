package model;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribu�dos
 */
public class Ideas {
    private ArrayList<DetailsIdea> ideas;
    public Ideas(){

    }

    public ArrayList<DetailsIdea> getPortfolio(Features RMIServer,int userID) {
        setIdeas(new ArrayList<DetailsIdea>());
        try {
            if(RMIServer!=null){
                ArrayList<String> ideasList;
                ideasList = RMIServer.getuserIdeasDetails(userID,true);



                int i=0;
                while(i<ideasList.size()){
                    DetailsIdea idea = new DetailsIdea();
                    idea.setTitle(ideasList.get(i++));
                    idea.setIdeaID(ideasList.get(i++));
                    idea.setDescription(ideasList.get(i++));
                    idea.setIdeaValue(Double.parseDouble(ideasList.get(i++)));
                    int numberTopics =  Integer.parseInt( ideasList.get( i++ ));
                    int a=i;
                    while( i < ( a + numberTopics)){
                        idea.addTopics(ideasList.get(i++));
                    }
                    getIdeas().add(idea);
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO getIdeas"+e);
        }
        return getIdeas();
    }

    public void setIdeas(ArrayList<DetailsIdea> ideas) {
        this.ideas = ideas;
    }

    public ArrayList<DetailsIdea> getIdeas() {
        return ideas;
    }

    public int removeIdea(Features RMIServer, String ideaTitle, int userID){
        int result;
        try{
            result = RMIServer.deleteIdea(ideaTitle,userID);
        }catch(Exception e){
            System.out.println(e);
            result = -666;
        }
        return result;
    }
}
