package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDAO {

    /**
     * Inserts a new account into the database.
     * @param account The Account to create
     * @return The successfully created account
     */
    public Account insertAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO Account (username, password) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ResultSet generatedkeys = ps.getGeneratedKeys();

            if (generatedkeys.next()) {
                int account_id = generatedkeys.getInt(1);
                return new Account(account_id, account.getUsername(), account.getPassword());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Finds an account corresponding to a specified username.
     * @param username Username of the account
     * @return The account with the specified username, if it exists
     */
    public Account getAccountByUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM Account WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                int account_id = result.getInt("account_id");
                String password = result.getString("password");

                return new Account(account_id, username, password);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
}
