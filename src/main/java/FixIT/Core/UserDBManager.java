package FixIT.Core;

/**
 * This class contains query functionality that is general to users and is shared by subclasses CustomerDBManager and
 * StaffDBManager
 *
 * @param <T> the type of User object that passes through this class (either Customer or Staff)
 */
public abstract class UserDBManager<T extends User> extends DBManager {

    protected String userTable;

    protected UserDBManager(String userTable) {
        this.userTable = userTable;
    }

    /**
     * Used to insert a new user into the database
     *
     * @param user the user to insert into the database
     */
    protected abstract void insertUserToDB(T user);

    /**
     * Used to check if a user already exists before inserting a user into the database table during registration
     *
     * @param username the username to query for
     * @return true if the username exists in the DB, false otherwise
     */
    protected abstract boolean userExists(String username);

    /**
     * Used to get information about the user for the user's profile page
     *
     * @param username the username of the user whose profile should be queried for
     * @return a User object containing the user's profile
     */
    protected abstract T getUserProfile(String username);
}
