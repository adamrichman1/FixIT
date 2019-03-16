package FixIT.Customer;

import FixIT.Core.UserDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * This class contains database/query functionality specific to FixIT customers
 */
public class CustomerDBManager extends UserDBManager<Customer> {

    private static Logger logger = LoggerFactory.getLogger(CustomerDBManager.class);

    CustomerDBManager() {
        super("customers");
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
     * Used to insert a new Customer into the database
     *
     * @param user the Customer to insert into the database TODO - make this more unit-testable
     */
    @Override
    protected void insertUserToDB(Customer user) {
        String sql = "INSERT INTO " + userTable + " (username, password, email, name, address, appointmentHistory, " +
                "rating, creditCard, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        executeUpdate(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getName(),user.getAddress(),
                user.getAppointmentHistory(),user.getRating(),user.getCreditCard(),user.getBalance());
    }

    /**
     * Used to get information about the user for the user's profile page
     *
     * @param username the username of the Customer whose profile should be queried for
     * @return a Customer object containing the user's profile
     */
    @Override
    protected Customer getUserProfile(String username) {
        return populateCustomer(executeQuery("SELECT * FROM " + userTable + " WHERE username=?"));
    }

    /**
     * Used to create the customer table
     *
     * @param tableName the name of the table to create // TODO - finish this based on insert and integrate
     */
    private void createCustomerTable(String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
                "";
        executeUpdate(sql);
    }

    /**
     * Used to populate a customer object after querying the customer table for a ResultSet
     *
     * @param rs the ResultSet containing customer data
     * @return a populated Customer object TODO - fix this cast warning
     */
    private Customer populateCustomer(ResultSet rs) {
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


