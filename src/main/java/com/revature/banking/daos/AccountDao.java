package com.revature.banking.daos;

import com.revature.banking.models.Account;
import com.revature.banking.util.ConnectionFactory;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.*;
import java.sql.*;

public class AccountDao implements Crudable<Account>{

    @Override
    public Account create(Account newAccount) {
        System.out.println("Here is the newTrainer as it enters our DAO layer: "+ newTrainer); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // NEVER EVER EVER EVER EVER concatenate or directly use these strings inside of the sql statement
            // String sql = "insert into trainer value (" + newTrainer.getFname() + "," + newTrainer.getLname();

            // The commented out sql String is using default for auto-generating the ID ifyou used serial
            // String sql = "insert into trainer values (default, ?, ?, ?, ?, ?)"; // incomplete sql statement, with default if not specifiying columns
            String sql = "insert into trainer (id, account, account_type, balance, email) values (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newAccount.getId());
            System.out.println(newAccount.getEmail());

            // 1-indexed, so first ? starts are 1
            ps.setInt(1, newAccount.getId());
            ps.setString(2, newAccount.getAccount());
            ps.setString(3, newAccount.getAccount_type());
            ps.setInt(4, newAccount.getBalance());
            ps.setString(5, newAccount.getEmail());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new RuntimeException();
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
        Account[] trainers = new Account[10];
        // declaring index variable as an int and intiliazation witht he value of 0
        int index = 0; // we want to keep track of where we are placing each trainer from the file into the the array

        // TODO: we trying something here and passing an argument???
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from account";
            Statement s = conn.createStatement();

            // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            // TODO: Hey why are we using the sql variable string here?
            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                Account account = new Account();

                account.setId(rs.getInt("id")); // this column lable MUST MATCH THE DB
                account.setAccount(rs.getString("Account"));
                account.setAccount_type(rs.getString("account_type"));
                account.setBalance(rs.getInt("balance"));
                account.setEmail(rs.getString("email"));

                System.out.println("Inserted account into index" + index);
                accounts[index] = account;
                index++; // increment the index by 1, must occur after the trainer[index] re-assignment
                System.out.println("Going to the next line for our following index.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }



        System.out.println("Returning trainers infomation to user.");
        return accounts;
    }

    @Override
    public Account findById(String id) {


        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from trainer where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            Account trainer = new Account();

            account.setId(rs.getInt("id")); // this column lable MUST MATCH THE DB
            account.setAccount(rs.getString("account"));
            account.setAccount_type(rs.getString("account_type"));
            account.setBalance(rs.getInt("balance"));
            account.setEmail(rs.getString("email"));

            return account;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean update(Account updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public void checkEmail(String email) {
        String sql = "select email from trainer where email = " + email; // issues with SQL injection
    }
}