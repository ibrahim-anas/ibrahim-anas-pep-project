import Controller.SocialMediaController;
import io.javalin.Javalin;

import Model.Message;
import Util.ConnectionUtil;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.*;
import Model.Account;
import Service.*;

import java.util.*;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        // ConnectionUtil.resetTestDatabase();

        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();

        // HttpClient webClient = HttpClient.newHttpClient();
        // ObjectMapper om = new ObjectMapper();

        app.start(8080);

        // Thread.sleep(1000);

        // System.out.println("***************** Start request ******************");

        // HttpRequest postMessageRequest = HttpRequest.newBuilder()
        //         .uri(URI.create("http://localhost:8080/messages"))
        //         .POST(HttpRequest.BodyPublishers.ofString("{"+
        //                 "\"posted_by\":1, " +
        //                 "\"message_text\": \"hello message\", " +
        //                 "\"time_posted_epoch\": 1669947792}"))
        //         .header("Content-Type", "application/json")
        //         .build();
        // HttpResponse response = webClient.send(postMessageRequest, HttpResponse.BodyHandlers.ofString());
        
        // System.out.println(response);

        // int status = response.statusCode();

        // Message expectedResult = new Message(2, 1, "hello message", 1669947792);
        // System.out.println(response.body().toString());
        // Message actualResult = om.readValue(response.body().toString(), Message.class);
        


        // AccountDAO accountDAO = new AccountDAO();
        // MessageDAO messageDAO = new MessageDAO();
        // AccountService accountService = new AccountService();
        // MessageService messageService = new MessageService();

        // Account account1 = new Account("anas", "1234");
        // Message message = new Message(2, 1, "hello message", 1669947792);
        // Account account2 = new Account("anas1", "1234");

        // System.out.println("Account: " + accountService.createAccount(account1));
        // System.out.println("Message: " + messageDAO.insertMessage(message));
        
        // if (account.getUsername().length() > 0 && account.getPassword().length() >= 4 && accountDAO.getAccountByUsername(account.getUsername()) != null) {
        //     System.out.println("Account: " + accountDAO.getAccountByUsername("anas"));
        //     System.out.println("Account: " + accountDAO.insertAccount(account2));

        // }
        // if (account2.getUsername().length() > 0 && account2.getPassword().length() >= 4 && accountDAO.getAccountByUsername(account2.getUsername()) == null) {
        //     System.out.println("Test Account: " + accountDAO.insertAccount(account2));
        //     accountDAO.insertAccount(account2);
        // }

        // System.out.println("Test Account: " + accountService.createAccount(account2));


        // System.out.println("Account Object: " + acc.getUsername() + " " + acc.getPassword() + " " + acc.getUsername().length());
        // List<Account> accounts = accountService.getAccounts();

        // for (Account acc : accounts) {
        //     System.out.println("Account: " + acc);
        // }
    }
}
