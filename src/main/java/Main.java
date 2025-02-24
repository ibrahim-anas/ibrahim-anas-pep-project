import Controller.SocialMediaController;
import io.javalin.Javalin;

// import DAO.*;
// import Model.Account;
// import Service.*;

// import java.util.*;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);

        // AccountDAO accountDAO = new AccountDAO();
        // AccountService accountService = new AccountService();
        // Account account = new Account("anas", "1234");
        // Account account2 = new Account("anas", "1234");


        // System.out.println("Account: " + accountService.createAccount(acc));
        // if (account.getUsername().length() > 0 && account.getPassword().length() >= 4 && accountDAO.getAccountByUsername(account.getUsername()) != null) {
        //     System.out.println("Account: " + accountDAO.getAccountByUsername("anas"));
        //     System.out.println("Account: " + accountDAO.insertAccount(account2));

        // }
        // if (account2.getUsername().length() > 0 && account2.getPassword().length() >= 4 && accountDAO.getAccountByUsername(account2.getUsername()) == null) {
        //     System.out.println("Test Account: " + accountDAO.insertAccount(account2));
        //     accountDAO.insertAccount(account2);
        // }

        // System.out.println("Test Account: " + accountDAO.insertAccount(account2));


        // System.out.println("Account Object: " + acc.getUsername() + " " + acc.getPassword() + " " + acc.getUsername().length());
        // List<Account> accounts = accountService.getAccounts();

        // for (Account account : accounts) {
        //     System.out.println("Account: " + account);
        // }

    }
}
