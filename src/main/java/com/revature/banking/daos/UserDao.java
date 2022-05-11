package com.revature.banking.daos;

import com.revature.banking.exceptions.ResourcePersistanceException;
import com.revature.banking.models.User;
import com.revature.banking.util.ConnectionFactory;
import com.revature.banking.util.logging.Logger;

import java.io.*;
import java.sql.*;

public class UserDao implements Crudable<User>{

    private Logger logger = Logger.getLogger();

    @Override
    public User create(User newUser) {
        System.out.println("Here is the newUser as it enters our DAO layer: "+ newUser); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {


            String sql = "insert into users (email, password, first_name, last_name, age) values (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newUser.getFirst_name());
            System.out.println(newUser.getLast_name());

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newUser.getEmail());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getFirst_name());
            ps.setString(4, newUser.getLast_name());
            ps.setInt(5, Integer.parseInt(newUser.getAge()));

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new ResourcePersistanceException("User was not entered into database due to some issue.");
            }

        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return null;
        }
        return newUser;
    }

    @Override
    public User[] findAll() throws IOException {

        // making an array of Trainer classes, and seetting it to a max size of 10
        User[] users = new User[10];
        // declaring index variable as an int and intiliazation witht he value of 0
        int index = 0; // we want to keep track of where we are placing each trainer from the file into the the array

        // TODO: we trying something here and passing an argument???
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from users";
            Statement s = conn.createStatement();

            // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            // TODO: Hey why are we using the sql variable string here?
            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                User user = new User();

                user.setEmail(rs.getString("email")); // this column lable MUST MATCH THE DB
                user.setPassword(rs.getString("password"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setAge(rs.getString("age"));

                System.out.println("Inserted account into index" + index);
                users[index] = user;
                index++; // increment the index by 1, must occur after the trainer[index] re-assignment
                System.out.println("Going to the next line for our following index.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("Returning users information to user.");
        return users;
    }

    @Override
    public User findById(String id) {


        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from user where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            if(!rs.next()){
                throw new ResourcePersistanceException("User was not found in the database, please check ID entered was correct.");
            }

            User user = new User();

            user.setEmail(rs.getString("email")); // this column lable MUST MATCH THE DB
            user.setPassword(rs.getString("password"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setAge(rs.getString("age"));

            return user;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean update(User updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
    public User authenticateUser(String email, String password) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from users where email = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            User user = new User();

            user.setEmail(rs.getString("email")); // this column lable MUST MATCH THE DB
            user.setPassword(rs.getString("password"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setEmail(rs.getString("age"));

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
        public boolean checkEmail(String email) {

            try(Connection conn = ConnectionFactory.getInstance().getConnection()){
                String sql = "select email from users where email = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);

                ResultSet rs = ps.executeQuery();

                if(!rs.isBeforeFirst()){
                    return false;
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

}