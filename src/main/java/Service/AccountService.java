package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = new AccountDAO();
    }

    /**
     * Persists a new account.
     * @param account An account object
     * @return The successfully created account
     */
    public Account createAccount(Account account) {
        if (account.getUsername().length() > 0 && account.getPassword().length() >= 4 && this.accountDAO.getAccountByUsername(account.getUsername()) != null) {
            return this.accountDAO.insertAccount(account);
        }

        return null;
    }
}
