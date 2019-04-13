package FixIT.Customer;

import FixIT.Core.UserDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class contains database/query functionality specific to FixIT customers
 */
public class CustomerDBManager extends UserDBManager<Customer> {

    private static Logger logger = LoggerFactory.getLogger(CustomerDBManager.class);
    private static CustomerDBManager dbManager;

    private CustomerDBManager() {
        super("customers");
    }

    /**
     * Used for singleton design pattern.
     *
     * @return the singleton instance of this class
     */
    public static CustomerDBManager getInstance() {
        if (dbManager == null) {
            dbManager = new CustomerDBManager();
        }
        return dbManager;
    }

    /**
     * Used to update a customer's balance in the database
     *
     * @param username the username of the user whose balance is being updated
     * @param balance the new balance of the user TODO - integrate
     */
    public void updateBalance(String username, BigDecimal balance) {
        String sql = "UPDATE " + userTable + " SET balance=? WHERE username=?";
        executeUpdate(sql, balance, username);
    }

    /**
     * Used to insert a new user into the database
     *
     * @param user the user to insert into the database
     */
    protected void insertUserToDB(Customer user) {
        String sql = "INSERT INTO " + userTable + " (username, password, email, name, address, appointmentHistory, " +
                "rating, creditCard, balance) VALUES (?, ?, ?, ?, ?, ?, 5.0, ?, 0.0)";
        executeUpdate(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getAddress(),
                Arrays.toString(user.getAppointmentHistory().toArray()), user.getCreditCard());
    }

    /**
     * Used to create the customer table
     */
    public void createCustomerTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + userTable +
                " (username         TEXT        NOT NULL, " +
                "password           TEXT        NOT NULL, " +
                "email              TEXT        NOT NULL, " +
                "name               TEXT        NOT NULL, " +
                "address            TEXT        NOT NULL, " +
                "appointmentHistory TEXT        NOT NULL, " +
                "rating             DECIMAL     NOT NULL, " +
                "creditCard         TEXT        NOT NULL, " +
                "balance            DECIMAL     NOT NULL)";
        executeUpdate(sql);
    }

    /**
     * Used to populate a customer object after querying the customer table for a ResultSet
     *
     * @param rs the ResultSet containing customer data
     * @return a populated Customer object TODO - fix this cast warning
     */
    protected Customer populateUser(ResultSet rs) {
        try {
            if (rs.next()) {
                Customer c = new Customer(rs.getString("username"));
                c.setEmail(rs.getString("email"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setAppointmentHistory(deserializeString("appointmentHistory", ArrayList.class));
                c.setRating(rs.getDouble("rating"));
                c.setCreditCard(rs.getString("creditCard"));
                c.setBalance(rs.getBigDecimal("balance"));
                return c;
            }
        } catch (SQLException e) {
            logger.error(">>> ERROR: Couldn't populate Customer from ResultSet");
            System.exit(1);
        }
        return null;
    }
}


