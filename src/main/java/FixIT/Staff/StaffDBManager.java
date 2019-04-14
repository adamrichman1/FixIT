package FixIT.Staff;

import FixIT.Core.UserDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains database/query functionality specific to FixIT staff
 */
public class StaffDBManager extends UserDBManager<Staff> {

    private static Logger logger = LoggerFactory.getLogger(StaffDBManager.class);
    private static StaffDBManager dbManager;

    private StaffDBManager() {
        super("staff");
    }

    /**
     * Used for singleton design pattern.
     *
     * @return the singleton instance of this class
     */
    public static StaffDBManager getInstance() {
        if (dbManager == null) {
            dbManager = new StaffDBManager();
        }
        return dbManager;
    }

    /**
     * Used to create the staff table
     */
    public void createStaffTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + userTable +
                " (username         TEXT        NOT NULL, " +
                "password           TEXT        NOT NULL, " +
                "email              TEXT        NOT NULL, " +
                "name               TEXT        NOT NULL, " +
                "address            TEXT        NOT NULL, " +
                "appointmentHistory TEXT        NOT NULL, " +
                "rating             DECIMAL     NOT NULL)";
        executeUpdate(sql);
    }

    /**
     * Checks if the staff table has staff member in it
     *
     * @return true if there exists a staff member in the table, false otherwise
     */
    public boolean staffAvailable() {
        String sql = "SELECT COUNT(*) AS count FROM " + userTable;
        return deserializeResultSetCol(executeQuery(sql), "count", int.class) != 0;
    }

    /**
     * Checks if the staff table has staff member in it
     *
     * @return true if there exists a staff member in the table, false otherwise
     */
    public String findStaff() {
        String sql = "SELECT username AS username FROM " + userTable + " ORDER BY random() LIMIT 1";
        return getString(executeQuery(sql), "username");
    }

    /**
     * Used to insert a new staff member into the database
     *
     * @param user the staff member to insert into the database
     */
    protected void insertUserToDB(Staff user) {
        String sql = "INSERT INTO " + userTable + " (username, password, email, name, address, appointmentHistory, " +
                "rating) VALUES (?, ?, ?, ?, ?, ?, 0.0)";
        executeUpdate(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getAddress(),
                Arrays.toString(user.getAppointmentHistory().toArray()));
    }

    /**
     * Used to populate a customer object after querying the staff table for a ResultSet
     *
     * @param rs the ResultSet containing staff data
     * @return a populated Staff object
     */
    @Override
    protected Staff populateUser(ResultSet rs) {
        try {
            if (rs.next()) {
                Staff s = new Staff(rs.getString("username"));
                s.setEmail(rs.getString("email"));
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));
                s.setAppointmentHistory(deserializeString("appointmentHistory", ArrayList.class));
                s.setRating(rs.getDouble("rating"));
                return s;
            }
        } catch (SQLException e) {
            logger.error(">>> ERROR: Couldn't populate Customer from ResultSet");
            System.exit(1);
        }
        return null;
    }
}
