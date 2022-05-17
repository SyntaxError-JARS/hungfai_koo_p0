package com.revature.banking.daos;

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
        System.out.println("Here is the newAccount as it enters our DAO layer: "+ newAccount); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {


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

            if(checkInsert == 0){
                throw new ResourcePersistanceException("Account was not entered into database due to some issue.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                newAccount.setId((int)rs.getLong(1));
            }

        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return null;
        }
        return newAccount;
    }

    @Override
    public Account[] findAll() throws IOException {

        // making an array of Trainer classes, and seetting it to a max size of 10
        Account[] users = new Account[10];
        // declaring index variable as an int and intiliazation witht he value of 0
        int index = 0; // we want to keep track of where we are placing each trainer from the file into the the array

        // TODO: we trying something here and passing an argument???
        Account[] accounts = new Account[0];
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from users";
            Statement s = conn.createStatement();

            // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            // TODO: Hey why are we using the sql variable string here?
            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                Account account = new Account();

                account.setId(rs.getInt("id")); // this column label MUST MATCH THE DB
                account.setAccount(rs.getString("account"));
                account.setAccount_type(rs.getString("account_type"));
                account.setBalance(rs.getInt("balance"));
                account.setEmail(rs.getString("email"));

                System.out.println("Inserted account into index" + index);
                accounts[index] = account;
                index++; // increment the index by 1, must occur after the account[index] re-assignment
                System.out.println("Going to the next line for our following index.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("Returning accounts information to user.");
        return accounts;
    }

    @Override
    public Account findById(String id) {
        return null;
    }

    @Override
    public boolean update(Account updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }


}