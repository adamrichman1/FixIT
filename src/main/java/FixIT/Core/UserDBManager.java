package FixIT.Core;

/**
 * This class contains query functionality that is general to users and is shared by subclasses CustomerDBManager and
 * StaffDBManager
 *
 * @param <T> the type of User object that passes through this class (either Customer or Staff)
 */
public abstract class UserDBManager<T extends User> extends DBManager {

    /**
     * Used to insert a user into the database - generic so specific types of users can be inserted
     *
     * @param user the user to insert into the database
     */
    public void insertUserToDB(T user) {
        // TODO
    }
}
