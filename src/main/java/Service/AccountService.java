package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService() {
        this.accountDAO = new AccountDAO();
    }

    /**
     * Persists a new account.
     * @param account An account object
     * @return The successfully created account
     */
    public Account createAccount(Account account) {
        if (account.getUsername().length() > 0 && account.getPassword().length() >= 4 && this.accountDAO.getAccountByUsername(account.getUsername()) == null) {
            return this.accountDAO.insertAccount(account);
        }
        
        return null;
    }

    /**
     * Checks to make sure the provided username and password match those of an existing account
     * @param account
     * @return The account with a match to the provided account object
     */
    public Account verifyLogin(Account account) {
        return accountDAO.getAccountByUserAndPass(account);
    }
}
