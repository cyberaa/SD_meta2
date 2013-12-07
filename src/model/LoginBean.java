package model;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;


/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribu�dos
 */
public class LoginBean {

    private String userName;
    private String password;
    private String AppSecret = "af8edf703b7a95f5966e9037b545b7ce";
    private double deiCoins;
    private String token;

    public int login(Features RMIServer){
        int answer = 0;
        try {
            if(RMIServer!=null){
                answer = RMIServer.Login(userName,password);
            }else{
                answer=-666;
            }
        } catch (Exception e) {
            System.out.println(e);
            answer=-666;
        }
        return answer;
    }

    public int loginFace(Features RMIServer, String token) {
        System.out.println("MERDAS LOGIN FACE");
        System.out.println("TOKEN :" + token);
        int answer = 0;
        String idFacebook = "";

        OAuthService service = new ServiceBuilder()
                .provider(FacebookApi.class)
                .apiKey("436480809808619")
                .apiSecret("af8edf703b7a95f5966e9037b545b7ce")
                .callback("http://localhost:8080")   //should be the full URL to this action
                .build();
        OAuthRequest authRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me?access_token="+ token);
        Token token_final = new Token(token,AppSecret);
        service.signRequest(token_final, authRequest);
        Response authResponse = authRequest.send();

        try {
            userName = new JSONObject(authResponse.getBody()).getString("name");
            idFacebook = new JSONObject(authResponse.getBody()).getString("id");

        } catch (JSONException e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n " + e);
            e.printStackTrace();
            //FIXME WHAT TO DO WITH THIS?????
        }
        System.out.println("USERNAME " + userName);
        System.out.println("idFacebook " + userName);
        if (idFacebook == null)
            return -1;

        //RMI PARA REGISTAR / LOGIN
         //idFacebook, userName
        System.out.println("USERNAME " + userName);
        System.out.println("idFacebook " + userName);
        try {
            answer = RMIServer.authenticateFacebook(idFacebook, userName);
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        return answer;


    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=hashPassword(password);
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
    private String hashPassword(String pass)    {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Cannot find hashing algorithm:\n" + e);
            System.exit(-1);
        }
        if(m == null)
        {
            System.out.println("Cannot find hashing algorithm.");
            return null;
        }
        m.reset();
        m.update(pass.getBytes());

        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashText = bigInt.toString(16);

        while(hashText.length() < 32)
            hashText = "0" + hashText;

        return hashText;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
