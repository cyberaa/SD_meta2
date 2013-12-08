package model;

import java.util.ArrayList;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class Ideas {
    private ArrayList<DetailsIdea> ideas;
    public Ideas(){

    }

    public ArrayList<DetailsIdea> getIdeas(Features RMIServer,int userID,String ideaTitle) {
        setIdeas(new ArrayList<DetailsIdea>());
        try {
            if(RMIServer!=null){
                ArrayList<String> ideasList;
                ideasList = RMIServer.getuserIdeasDetails(userID,true,ideaTitle);
                if(ideasList==null){
                    return null;
                }
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
            System.out.println("ERRO [Ideas] getIdeas"+e);
        }
        return getIdeas();
    }

    public void setIdeas(ArrayList<DetailsIdea> ideas) {
        this.ideas = ideas;
    }

    public ArrayList<DetailsIdea> getIdeas() {
        return ideas;
    }

    public int removeIdea(Features RMIServer, String ideaTitle, int userID, String Token){
        int result;
        try{
            result = RMIServer.deleteIdea(ideaTitle, userID,Token);
        }catch(Exception e){
            System.out.println(e);
            result = -666;
        }
        return result;
    }

    public int setSharePrice(Features RMIServer, String ideaTitle, int userID, double finalPrice){
        ArrayList<String> result;
        try{
            result = RMIServer.setSharePrice(ideaTitle, finalPrice, userID);
            if(result == null){
                return -1;
            }
        }catch(Exception e){
            System.out.println(e);
            return -666;
        }
        return 0;
    }

    public int buyShares(Features RMIServer, String ideaTitle, int userID, String n_shares, String maxprice, String finalPrice){
        ArrayList<String> result;
        try{
            int numberShares = Integer.parseInt(n_shares);
            double finalP = Double.parseDouble(finalPrice);
            double max = Double.parseDouble(maxprice);
            result = RMIServer.buyShares(ideaTitle,numberShares,userID,finalP,max,null);
            if(result == null){
                return -1;
            }
            if(result.size()==0){
                return 0;
            }
            /*FIXME add Notifications?*/
        }catch(Exception e){
            System.out.println(e);
            return -666;
        }
        return 1;
    }

    public int buySharesFacebook(Features RMIServer, String ideaTitle, int userID, String n_shares, String maxprice, String finalPrice,String token){
        ArrayList<String> result;
        try{
            int numberShares = Integer.parseInt(n_shares);
            double finalP = Double.parseDouble(finalPrice);
            double max = Double.parseDouble(maxprice);
            result = RMIServer.buySharesFacebook(ideaTitle,numberShares,userID,finalP,max,null,token);
            if(result == null){
                return -1;
            }
            if(result.size()==0){
                return 0;
            }
            /*FIXME add Notifications?*/
        }catch(Exception e){
            System.out.println(e);
            return -666;
        }
        return 1;
    }

    public int addToWatchListStatus(Features RMIServer, String IdeaID, int userID){
        int result;
        try{
            int idea = Integer.parseInt(IdeaID);
            result = RMIServer.addToWatchList(userID,idea);
        }catch(Exception e){
            System.out.println(e);
            result = -666;
        }
        return result;
    }

    public int removeFromWatchListStatus(Features RMIServer, String IdeaID, int userID){
        int result;
        try{
            int idea = Integer.parseInt(IdeaID);
            result = RMIServer.removeFromWatchList(userID,idea);
        }catch(Exception e){
            System.out.println(e);
            result = -666;
        }
        return result;
    }

    public ArrayList<DetailsIdea> getWatchList(Features RMIServer,int userID) {
        setIdeas(new ArrayList<DetailsIdea>());
        try {
            if(RMIServer!=null){
                ArrayList<String> ideasList;
                ideasList = RMIServer.viewWatchList(userID);
                if(ideasList==null){
                    return null;
                }
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
            System.out.println("ERRO [Ideas] getWatchList"+e);
        }
        return getIdeas();
    }

    public ArrayList<DetailsIdea> getHallOfFame(Features RMIServer) {
        setIdeas(new ArrayList<DetailsIdea>());
        try {
            if(RMIServer!=null){
                ArrayList<String> ideasList;
                ideasList = RMIServer.viewHallOfFame();
                if(ideasList==null){
                    return null;
                }
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
            System.out.println("ERRO [Ideas] getWatchList"+e);
        }
        return getIdeas();
    }

    public int takeOver(Features RMIServer , int UserId, String IdeaTitle){
        int result;
        try{
            result = RMIServer.takeover(UserId,IdeaTitle);
        }catch(Exception e){
            System.out.println(e);
            result = -666;
        }
        return result;
    }
}
