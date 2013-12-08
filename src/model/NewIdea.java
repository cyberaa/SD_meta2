package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;

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
                if(answer!=-1 && this.getFileUpload()!=null){
                    byte[] file = readAllBytes(this.getFileUpload());
                    RMIServer.saveFile(answer,this.getFileUploadFileName(),file);
                }
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
    public int submitNewIdeaFacebook(Features RMIServer,int userID,String token) {
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
                answer = RMIServer.newIdeaFacebook(data,userID,false,token);
                if(answer!=-1 && this.getFileUpload()!=null){
                    byte[] file = readAllBytes(this.getFileUpload());
                    RMIServer.saveFile(answer,this.getFileUploadFileName(),file);
                }
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

    /**
     * Method to conver a file to a byte array, in order to send through socket
     * @param file to be converted
     * @return byte[] of the file
     * @throws IOException Reached end of file
     */
    public byte[] readAllBytes(File file) throws IOException{

        byte []buffer = new byte[(int) file.length()];
        InputStream ios = null;
        try {
            ios = new FileInputStream(file);
            if ( ios.read(buffer) == -1 ) {
                throw new IOException("EOF reached while trying to read the whole file");
            }
        } finally {
            try {
                if ( ios != null )
                    ios.close();
            } catch ( IOException e) {
            }
        }
        return buffer;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }
}
