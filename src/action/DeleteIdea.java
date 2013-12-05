package action;

import model.Features;
import model.Ideas;

/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */
public class DeleteIdea extends Action {
    private Ideas ideas;
    private String IdeaTitle;
    private boolean retry;
    private char DeleteResult;

    public String execute(){
        Features server = getRMIserver();
        int userID = getUserID();
        if (IdeaTitle == null){
            DeleteResult = '1';
            System.out.println("IdeaTitle=null");
            return "ERROR";
        }
        ideas = new Ideas();
        int answer = ideas.removeIdea(server,IdeaTitle,userID);
        if (answer==-666){
            if (retry==false){
                retry=true;
                execute();
            }
            return "RMIERROR";
        }else if(answer == -1){
            DeleteResult = '1';
            System.out.println("Não podes eliminar a ideia");
            return "ERROR";
        }
        DeleteResult = '2';
        return "SUCCESS";
    }

    public String getIdeaTitle() {
        return IdeaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        IdeaTitle = ideaTitle;
    }

    public char getDeleteResult() {
        return DeleteResult;
    }

    public void setDeleteResult(char deleteResult) {
        DeleteResult = deleteResult;
    }
}
