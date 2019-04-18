package FixIT.Customer;

import FixIT.Core.UserDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * This class contains database/query functionality specific to FixIT customers
 */
public class CustomerDBManager extends UserDBManager<Customer> {

    private static CustomerDBManager dbManager;
    private static Logger logger = LoggerFactory.getLogger(CustomerDBManager.class);

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
                "creditCard         TEXT        NOT NULL)";
        executeUpdate(sql);
    }

    /**
     * Used to insert a new user into the database
     *
     * @param user the user to insert into the database
     * @return the Customer object inserted
     */
    protected Customer insertUserToDB(Customer user) {
        String sql = "INSERT INTO " + userTable + " (username, password, email, name, address, appointmentHistory, " +
                "creditCard) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING *";
        return populateUser(executeUpdate(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getAddress(),
                Arrays.toString(user.getAppointmentHistory().toArray()), user.getCreditCard()));
    }

    /**
     * Used to populate a customer object after querying the customer table for a ResultSet
     *
     * @param rs the ResultSet containing customer data
     * @return a populated Customer object
     */
    private Customer populateUser(ResultSet rs) {
        try {
            if (rs.next()) {
                Customer c = new Customer(rs.getString("username"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setCreditCard(rs.getString("creditCard"));
                return c;
            }
        } catch (SQLException e) {
            logger.error(">>> ERROR: Couldn't populate Customer from ResultSet");
            System.exit(1);
        }
        return null;
    }
}


