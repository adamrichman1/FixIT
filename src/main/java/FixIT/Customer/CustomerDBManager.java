package FixIT.Customer;

import FixIT.Core.UserDBManager;

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

    }

    /**
     * Used to insert a new Customer into the database
     *
     * @param user the Customer to insert into the database
     */
    @Override
    protected void insertUserToDB(Customer user) {

    }

    /**
     * Used to check if a customer already exists before inserting a customer into the database table during registration
     *
     * @param username the username to query for
     * @return true if the username exists in the DB, false otherwise
     */
    @Override
    protected boolean userExists(String username) {
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
        return null;
    }
}
