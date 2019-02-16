package FixIT.Staff;

import FixIT.Core.UserDBManager;

/**
 * This class contains database/query functionality specific to FixIT staff
 */
public class StaffDBManager extends UserDBManager<Staff> {

    protected StaffDBManager() {
        super("staff");
    }

    /**
     * Used to insert a new staff member into the database
     *
     * @param user the staff member to insert into the database
     */
    @Override
    protected void insertUserToDB(Staff user) {

    }

    /**
     * Used to check if a staff member already exists before inserting them into the database table during registration
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
     * @param username the username of the staff member whose profile should be queried for
     * @return a Staff object containing the staff member's profile
     */
    @Override
    protected Staff getUserProfile(String username) {
        return null;
    }
}
