package FixIT.Core;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains query functionality specific to Appointments
 */
public class AppointmentDBManager extends DBManager {
    private static String appointmentTable = "appointments";
    private static Logger logger = LoggerFactory.getLogger(AppointmentDBManager.class);

    public static void createAppointmentTable() {
        executeUpdate("CREATE TABLE IF NOT EXISTS " + appointmentTable +
                " (problem              TEXT        NOT NULL," +
                " customerUsername      TEXT        NOT NULL," +
                " staffUsername         TEXT        NOT NULL," +
                " appointmentTime       TEXT        NOT NULL," +
                " worklog               TEXT        NOT NULL," +
                " appointmentStatus     INT         NOT NULL," +
                " customerRating        INT         NOT NULL," +
                " staffRating           INT         NOT NULL," +
                " appointmentCost       REAL        NOT NULL," +
                " appointmentID SERIAL PRIMARY KEY  NOT NULL)");
    }

    /**
     * Finds the average rating for a customer
     *
     * @param username the username of the customer
     * @return the customer's rating
     */
    public static double getCustomerRating(String username) {
        String sql = "SELECT AVG(customerRating) AS avg FROM " + appointmentTable + " WHERE customerUsername=?";
        return deserializeResultSetCol(executeQuery(sql, username), "avg", double.class);
    }

    /**
     * Finds the average rating for a staff
     *
     * @param username the username of the staff
     * @return the staff's rating
     */
    public static double getStaffRating(String username) {
        String sql = "SELECT AVG(staffRating) AS avg FROM " + appointmentTable + " WHERE staffUsername=?";
        return deserializeResultSetCol(executeQuery(sql, username), "avg", double.class);
    }

    /**
     * Drops the appointment table
     */
    public static void dropAppointmentTable() {
        dropTable(appointmentTable);
    }

    /**
     * Finds an appointment in the database
     *
     * @param appointmentID the ID of the appointment to locate
     * @return an Appointment object
     */
    public static Appointment findAppointment(long appointmentID) {
        String sql = "SELECT * FROM " + appointmentTable + " WHERE appointmentID=?";
        return populateAppointment(executeQuery(sql, appointmentID));
    }

    /**
     * Used to insert an appointment into the appointment table once requested by a customer
     *
     * @param problem the problem the appointment is for
     * @param customerUsername the username of the customer
     * @param staffUsername the username of the staff member
     * @param appointmentTime the appointmentTime of the appointment
     * @param worklog the appointment's worklog
     * @param appointmentStatus the status of the appointment
     * @param customerRating the customer's rating for the appointment
     * @param staffRating the staff member's rating for the appointment
     * @param appointmentCost the cost of the appointment
     */
    void insertAppointmentToDB(String problem, String customerUsername, String staffUsername,
                               String appointmentTime, List<String> worklog, int appointmentStatus,
                               int customerRating, int staffRating, BigDecimal appointmentCost) {
        String sql = "INSERT INTO " + appointmentTable + " (problem, customerUsername, staffUsername, " +
                "appointmentTime, worklog, appointmentStatus, customerRating, staffRating, appointmentCost) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING *";
        executeUpdate(sql, problem, customerUsername, staffUsername, appointmentTime, new JSONArray(worklog).toString(),
                appointmentStatus, customerRating, staffRating, appointmentCost);
    }

    /**
     * Used so the user can view all of their appointments in the past, present, and future
     *
     * @param customerUsername the username of the customer whose appointments are being queried for
     * @return an ArrayList of Appointment objects for the user
     */
    ArrayList<Appointment> findCustomerAppointmentHistory(String customerUsername) {
        String sql = "SELECT * FROM " + appointmentTable + " WHERE customerUsername=? ORDER BY appointmentStatus ASC, " +
                "appointmentID DESC";
        return populateAppointmentHistory(executeQuery(sql, customerUsername));
    }

    /**
     * Used so the user can view all of their appointments in the past, present, and future
     *
     * @param staffUsername the username of the staff member whose appointments are being queried for
     * @return an ArrayList of Appointment objects for the user
     */
    ArrayList<Appointment> findStaffAppointmentHistory(String staffUsername) {
        String sql = "SELECT * FROM " + appointmentTable + " WHERE staffUsername=? ORDER BY appointmentStatus ASC, " +
                "appointmentID DESC";
        return populateAppointmentHistory(executeQuery(sql, staffUsername));
    }

    /**
     * Populates appointment history following a DB query
     *
     * @param rs the ResultSet object containing data from the DB
     * @return a List of Appointment objects
     */
    private static ArrayList<Appointment> populateAppointmentHistory(ResultSet rs) {
        ArrayList<Appointment> list = new ArrayList<>();
        Appointment a;
        while ((a = populateAppointment(rs)) != null) {
            list.add(a);
        }
        return list;
    }

    /**
     * Populates an appointment following a DB query
     *
     * @param rs the ResultSet object containing data from the DB
     * @return an Appointment object, or null if there is not an appointment object present
     */
    private static Appointment populateAppointment(ResultSet rs) {
        try {
            if (rs.next()) {
                Appointment a = new Appointment();
                a.setProblem(rs.getString("problem"));
                a.setCustomerUsername(rs.getString("customerUsername"));
                a.setStaffUsername(rs.getString("staffUsername"));
                a.setAppointmentTime(rs.getString("appointmentTime"));
                a.setWorkLog(deserializeString(rs.getString("worklog"), ArrayList.class));
                a.setAppointmentStatus(rs.getInt("appointmentStatus"));
                a.setCustomerRating(rs.getInt("customerRating"));
                a.setStaffRating(rs.getInt("staffRating"));
                a.setAppointmentCost(rs.getBigDecimal("appointmentCost"));
                a.setAppointmentID(rs.getLong("appointmentID"));
                return a;
            }
        } catch (SQLException e) {
            logger.error(">>> ERROR: Couldn't populate appointment object", e);
            System.exit(1);
        }
        return null;
    }

    /**
     * Used to adjust the status of an appointment (0=not-started, 1=in-progress, 2=completed)
     *
     * @param appointmentID the id of the appointment to update
     * @param appointmentStatus the new status of the appointment
     */
    void updateAppointmentStatus(long appointmentID, int appointmentStatus) {
        String sql = "UPDATE " + appointmentTable + " SET appointmentStatus=? WHERE appointmentID=?";
        executeUpdate(sql, appointmentStatus, appointmentID);
    }

    /**
     * Used to adjust the status of an appointment (0=not-started, 1=in-progress, 2=completed)
     *
     * @param appointmentID the id of the appointment to update
     * @param worklog the updated worklog for the appointment
     */
    void updateAppointmentWorklog(long appointmentID, List<String> worklog) {
        String sql = "UPDATE " + appointmentTable + " SET worklog=? WHERE appointmentID=?";
        executeUpdate(sql, new JSONArray(worklog).toString(), appointmentID);
    }

    /**
     * Used to adjust the staff rating column once a customer rates the staff member following an appointment
     *
     * @param appointmentID the id of the appointment to update
     * @param staffRating the rating for the staff member
     */
    void addStaffRatingToDB(long appointmentID, int staffRating) {
        String sql = "UPDATE " + appointmentTable + " SET staffRating=? WHERE appointmentID=?";
        executeUpdate(sql, staffRating, appointmentID);
    }

    /**
     * Used to adjust the customer rating column once a staff member rates the customer following an appointment
     *
     * @param appointmentID the id of the appointment to update
     * @param customerRating the rating for the customer
     * */
    void addCustomerRatingToDB(long appointmentID, int customerRating) {
        String sql = "UPDATE " + appointmentTable + " SET customerRating=? WHERE appointmentID=?";
        executeUpdate(sql, customerRating, appointmentID);
    }
}
