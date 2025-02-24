package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
// import java.util.*;

public class AccountDAO {

    /**
     * Inserts an account into the account table.
     * @param account The Account to create
     * @return The successfully created account
     */
    public Account insertAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO account (username, password) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                int account_id = generatedKeys.getInt("account_id");
                return new Account(account_id, account.getUsername(), account.getPassword());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    /**
     * Finds the account with the specified username.
     * @param username 
     * @return The account with the specified username, if it exists
     */
    public Account getAccountByUserAndPass(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                int account_id = result.getInt("account_id");

                return new Account(account_id, account.getUsername(), account.getPassword());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }


     /**
     * Finds the account with the specified username.
     * @param username 
     * @return The account with the specified username, if it exists
     */
    public Account getAccountByUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM account WHERE username = ?";
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


    /**
     * Finds the account with the specified account id.
     * @param account_id 
     * @return The account with the specified account id, if it exists
     */
    public Account getAccountByID(int account_id) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, account_id);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");

                return new Account(account_id, username, password);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }


    // public List<Account> getAccounts() {
    //     Connection connection = ConnectionUtil.getConnection();
    //     List<Account> accounts = new ArrayList<>();

    //     try {
    //         String sql = "SELECT * FROM account";
    //         PreparedStatement ps = connection.prepareStatement(sql);

    //         ResultSet rs = ps.executeQuery();

    //         while (rs.next()) {
    //             int account_id = rs.getInt("account_id");
    //             String username = rs.getString("username");
    //             String password = rs.getString("password");

    //             accounts.add(new Account(account_id, username, password));
    //         }

    //         return accounts;

    //     } catch (SQLException e) {
    //         System.out.println(e.getMessage());
    //     }
        
    //     return null;
    // }
}
