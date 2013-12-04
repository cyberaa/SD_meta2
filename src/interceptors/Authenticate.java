package interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import model.Client;
import java.util.Map;
/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuídos
 */

public class Authenticate implements Interceptor {

        private static final long serialVersionUID = -5011962009065225959L;

        @Override
        public void destroy() {
            //release resources here
        }

        @Override
        public void init() {
            System.out.println("Initializing MyLoggingInterceptor...");
        }

        @Override
        public String intercept(ActionInvocation actionInvocation) throws Exception {
            Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();

            Client user = (Client) sessionAttributes.get("client");
            System.out.println("lol");
            if(user == null) {
                sessionAttributes.put("client", new Client());
                return "LOGIN";
            }
            else {
                int userID = ((Client) sessionAttributes.get("client")).getUserID();
                System.out.println("userid:"+userID);
                if(userID > 0) {
                    return actionInvocation.invoke();
                }
                return "LOGIN";
            }
        }
}
