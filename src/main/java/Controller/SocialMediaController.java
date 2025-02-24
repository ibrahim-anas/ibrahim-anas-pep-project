package Controller;

import Service.AccountService;
import Service.MessageService;
import Model.Account;
import Model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::postAuthenticationHandler);
        // app.post("/messages", null);
        return app;
    }

    /**
     * The POST request handler for the /register endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postAccountHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account matchingAccount = accountService.createAccount(account);
        
        if (matchingAccount != null) {
            context.json(om.writeValueAsString(matchingAccount));
        } else {
            context.status(400);
        }
    }


    /**
     * The POST request handler for the /login endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postAuthenticationHandler(Context context) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(context.body(), Account.class);
        Account matchingAccount = accountService.verifyLogin(account);

        if (matchingAccount != null) {
            context.json(om.writeValueAsString(matchingAccount));
        } else {
            context.status(401);
        }
    }
}