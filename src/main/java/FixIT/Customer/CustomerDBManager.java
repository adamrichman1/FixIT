package FixIT.Customer;

import FixIT.Core.UserDBManager;
//import FixIT.Core.Appointment;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;


/**
 * This class contains database/query functionality specific to FixIT customers
 */
public class CustomerDBManager extends UserDBManager<Customer> {

    protected CustomerDBManager() {
        super("customers");
    }

    /**
     * Used to update a customer's balance in the database
     *
     * @param user the Customer object containing customer data, including the customer's updated balance
     */
    public void updateBalance(Customer user) {
        try{
            // Create SQL Update statement
            String sql = "UPDATE CUSTOMERS SET BALANCE = ? WHERE USERNAME = ?";

            // execute the update
            executeUpdate(sql,user.getBalance(),user.getUsername());

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Used to insert a new Customer into the database
     *
     * @param user the Customer to insert into the database
     */
    @Override
    protected void insertUserToDB(Customer user) {
        try{
            String sql = "INSERT INTO CUSTOMERS " +
                            "(USERNAME, PASSWORD, EMAIL, NAME, ADDRESS, APPOINTMENT, RATING, CREDITCARD, BALANCE) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            executeUpdate(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getName(),user.getAddress(),
                    user.getAppointmentHistory(),user.getRating(),user.getCreditCard(),user.getBalance());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Used to check if a customer already exists before inserting a customer into the database table during registration
     *
     * @param username the username to query for
     * @return true if the username exists in the DB, false otherwise
     */
    @Override
    protected boolean userExists(String username) {
        try{

            String sql = "SELECT * FROM CUSTOMERS WHERE USERNAME = ? ";

            // execute the update
            ResultSet res = executeQuery(sql,username);


            if (!res.isBeforeFirst()  ) {
                return false; //no user with that username
            }
            else{
                return true; //user in set
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Used to get information about the user for the user's profile page
     *
     * @param username the username of the Customer whose profile should be queried for
     * @return a Customer object containing the user's profile
     */
    @Override
    protected Customer getUserProfile(String username) {
        try{

            ResultSet rs = executeQuery("SELECT * FROM CUSTOMERS WHERE USERNAME = "+username);
            if (!rs.isBeforeFirst()  ) {
                return null; //no user with that username
            }
            else{
                Customer rsCustomer = new Customer(rs.getNString(1));
                rsCustomer.setPassword(rs.getNString(2));
                rsCustomer.setEmail(rs.getNString(3));
                rsCustomer.setName(rs.getNString(4));
                rsCustomer.setAddress(rs.getNString(5));

                // I didn't know how to do this
                rsCustomer.setAppointmentHistory(rs.getObject(6, ArrayList<Appointment>));

                rsCustomer.setRating(rs.getDouble(7));
                rsCustomer.setCreditCard(rs.getNString(8));
                rsCustomer.setBalance(rs.getBigDecimal(9));
                return rsCustomer;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }




    /**
     * Used by CustomerDBManager to create specific types of tables (e.g. appointment table)
     *
     * @param dbURL the URL of the DB to connect to
     * @param sql the SQL string containing a query to create a table
     */
    protected void createTable(String dbURL, String sql) {
        try{


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Used to insert a new Customer into the database
     */

    protected void insert1CustomerRow() {
        try{
           
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}


