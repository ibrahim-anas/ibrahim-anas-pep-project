package Controller;

import Service.AccountService;
import Service.MessageService;
import Model.Account;
import Model.Message;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.*;

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
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByID);

        return app;
    }

    /**
     * The POST request handler for the /register endpoint.
     * @param context The Javalin Context object
     */
    private void postAccountHandler(Context context) {
        Account account = context.bodyAsClass(Account.class);
        Account matchingAccount = accountService.createAccount(account);
        
        if (matchingAccount != null) {
            context.json(matchingAccount);
        } else {
            context.status(400);
        }
    }

    /**
     * The POST request handler for the /login endpoint.
     * @param context The Javalin Context object
     */
    private void postAuthenticationHandler(Context context) {
        Account account = context.bodyAsClass(Account.class);
        Account matchingAccount = accountService.verifyLogin(account);

        if (matchingAccount != null) {
            context.json(matchingAccount);
        } else {
            context.status(401);
        }
    }

    /**
     * The POST request handler for the /messages endpoint.
     * @param context The Javalin Context object
     */
    private void postMessageHandler(Context context) {
        Message message = context.bodyAsClass(Message.class);
        Message createdMessage = messageService.createMessage(message);

        if (createdMessage != null) {
            context.json(createdMessage);
        } else {
            context.status(400);
        }
    }

    /**
     * The GET request hangler for the /messages endpoint.
     * @param context The Javalin Context object
     */
    public void getAllMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();

        context.json(messages);
    }

    /**
     * The GET request hangler for the /messages/{message_id} endpoint.
     * @param context The Javalin Context object
     */
    public void getMessageByIdHandler(Context context) {
        int message_id = Integer.parseInt(context.pathParam("message_id")); 
        Message message = messageService.getMessageByID(message_id);

        if (message != null) {
            context.json(message);
        } else {
            context.json("");
        }
    }

    public void deleteMessageByID(Context context) {
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.deleteMessageByID(message_id);

        if (message != null) {
            context.json(message);
        } else {
            context.json("");
        }
    }
}