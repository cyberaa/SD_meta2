package interceptors;

import com.opensymphony.xwork2.Action;
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
            System.out.println("inside auth interceptor");
            Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();

            Client user = (Client) sessionAttributes.get("user");

            if(user == null) {
                System.out.println("Bean will be created");
                sessionAttributes.put("user", new Client());
                return "LOGIN";
            }
            else {
                System.out.println("Bean in session");
            /*User action = (User) actionInvocation.getAction();

            int user_id = action.getUser().getUserID();*/

                int user_id = ((Client) sessionAttributes.get("user")).getUserID();
                System.out.println(user_id + "\n");
                if(user_id != -1 && user_id != 0) {
                    System.out.println("User logged!");
                    return actionInvocation.invoke();
                }
                return "LOGIN";
            }
        }
}
