package com.revature.banking.daos;

import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistanceException;
import com.revature.banking.models.Account;
import com.revature.banking.util.ConnectionFactory;
import com.revature.banking.util.logging.Logger;

import java.io.IOException;
import java.sql.*;

public class AccountDao implements Crudable<Account> {

    private Logger logger = Logger.getLogger();

    @Override
    public Account create(Account newAccount) {
        System.out.println("Here is the newAccount as it enters our DAO layer: " + newAccount); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {


            String sql = "insert into users_1nf (id, account, account_type, balance, email) values (default, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            System.out.println(newAccount.getId());
            System.out.println(newAccount.getEmail());

            // 1-indexed, so first ? starts are 1
//            ps.setInt(1, newAccount.getId());
            ps.setString(1, newAccount.getAccount());
            ps.setString(2, newAccount.getAccount_type());
            ps.setDouble(3, newAccount.getBalance());
            ps.setString(4, newAccount.getEmail());

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new ResourcePersistanceException("Account was not entered into database due to some issue.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                newAccount.setId((int) rs.getLong(1));
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
        return newAccount;
    }

    @Override
    public Account[] findAll() throws IOException {
        return null;
    }


    @Override
    public Account findByEmail(String email) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select account_balance, username from account where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ResourcePersistanceException("Username was not found in the database.");
            }

            Account accountBalance = new Account();
            accountBalance.setBalance(rs.getInt("balance"));
//           accountBalance.setAccountAction(rs.getString("account_action"));
            accountBalance.setEmail((rs.getString("email")));

            return accountBalance;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }



    public Account withdraw(Account createUpdate) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "Update users_1nf Set balance = balance - ? where email = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, createUpdate.getBalance());
            ps.setString(2, createUpdate.getEmail());


            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException();
            }

            int ab = createUpdate.getBalance();

//            if (af < ab) {
//                throw new InvalidRequestException("Account could not withdraw due to insufficient funds ");
//            }

            if (ab < 0) {
                throw new InvalidRequestException("Account could not withdraw due to a negative withdraw amount");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    return createUpdate;
    }

    public Account deposit(Account createUpdate) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "Update users_1nf Set balance = balance + ? where email = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, createUpdate.getBalance());
            ps.setString(2, createUpdate.getEmail());


            int checkInsert = ps.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
        return createUpdate;
    }


    public Account findById(String id) {
        return null;
    }


    public boolean update(Account updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }


}