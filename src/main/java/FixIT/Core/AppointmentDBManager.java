package FixIT.Core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static void createAppointmentTable() {
        executeUpdate("CREATE TABLE IF NOT EXISTS " + appointmentTable +
                " (customerUsername     TEXT        NOT NULL," +
                " staffUsername         TEXT        NOT NULL," +
                " appointmentTime       BIGINT      NOT NULL," +
                " worklog               TEXT        NOT NULL," +
                " appointmentStatus     INT         NOT NULL," +
                " customerRating        INT         NOT NULL," +
                " staffRating           INT         NOT NULL," +
                " appointmentCost       REAL        NOT NULL," +
                " appointmentID SERIAL PRIMARY KEY  NOT NULL)");
    }

    /**
     * Used to insert an appointment into the appointment table once requested by a customer
     *
     * @param customerUsername the username of the customer
     * @param staffUsername the username of the staff member
     * @param appointmentTime the appointmentTime of the appointment
     * @param worklog the appointment's worklog
     * @param appointmentStatus the status of the appointment
     * @param customerRating the customer's rating for the appointment
     * @param staffRating the staff member's rating for the appointment
     * @param appointmentCost the cost of the appointment
     */
    public void insertAppointmentToDB(String customerUsername, String staffUsername, long appointmentTime, List<String> worklog,
                                      int appointmentStatus, int customerRating, int staffRating, int appointmentCost) {
        String sql = "INSERT INTO " + appointmentTable + " (customerUsername, staffUsername, appointmentTime, worklog, " +
                "appointmentStatus, customerRating, staffRating, appointmentCost) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        executeUpdate(sql, customerUsername, staffUsername, appointmentTime, worklog, appointmentStatus, customerRating, staffRating, appointmentCost);
    }

    /**
     * Used so the user can view all of their appointments in the past, present, and future
     *
     * @param customerUsername the username of the customer whose appointments are being queried for
     * @return an ArrayList of Appointment objects for the user
     */
    public ArrayList<Appointment> findAllCustomerAppointments(String customerUsername) {
        String sql = "SELECT * FROM " + appointmentTable + " WHERE customerUsername=?";
        return populateAppointmentHistory(executeUpdate(sql, customerUsername));
    }

    /**
     * Populates appointment history following a DB query
     *
     * @param rs the ResultSet object containing data from the DB
     * @return a List of Appointment objects
     */
    private ArrayList<Appointment> populateAppointmentHistory(ResultSet rs) {
        return null;
    }

    /**
     * Populates an appointment following a DB query
     *
     * @param rs the ResultSet object containing data from the DB
     * @return an Appointment object
     */
    private Appointment populateAppointment(ResultSet rs) {
        try {
            Appointment a = new Appointment();
            a.setCustomerUsername(rs.getString("customerUsername"));
            a.setStaffUsername(rs.getString("staffUsername"));
            a.setAppointmentTime(rs.getLong("appointmentTime"));
            a.setWorkLog(deserializeString(rs.getString("worklog"), ArrayList.class));
            a.setAppointmentStatus(rs.getInt("appointmentStatus"));
            a.setCustomerRating(rs.getInt("customerRating"));
            a.setStaffRating(rs.getInt("staffRating"));
            a.setAppointmentCost(rs.getBigDecimal("appointmentCost"));
            a.setAppointmentID(rs.getLong("appointmentID"));
            return a;
        } catch (SQLException e) {
            logger.error(">>> ERROR: Couldn't populate customer object", e);
            System.exit(1);
        }
        return null;
    }

    /**
     * Used so the user can view all of their appointments in the past, present, and future
     *
     * @param customerUsername the username of the customer whose appointments are being queried for
     * @return an ArrayList of Appointment objects for the user
     */
    public ArrayList<Appointment> findAllStaffAppointments(String customerUsername) {
        return null;
    }

    /**
     * Used to update the worklog column in the appointments table after a staff member updates the worklog
     *
     * @param appointment the appointment object containing appointment data as well as the updated worklog data
     */
    public void updateAppointmentWorklog(Appointment appointment) {

    }

    /**
     * Used to adjust the time of an appointment if a customer requests a time change
     *
     * @param appointment the appointment object containing appointment data as well as the updated appointment time
     */
    public void updateAppointmentTime(Appointment appointment) {

    }

    /**
     * Used to adjust the status of an appointment (0=not-started, 1=in-progress, 2=completed)
     *
     * @param appointment the appointment object containing appointment data as well as the updated appointment status
     */
    public void updateAppointmentStatus(Appointment appointment) {

    }

    /**
     * Used to adjust the staff rating column once a customer rates the staff member following an appointment
     *
     * @param appointment the appointment object containing appointment data as well as the updated staff rating
     */
    public void addStaffRatingToDB(Appointment appointment) {

    }

    /**
     * Used to adjust the customer rating column once a staff member rates the customer following an appointment
     *
     * @param appointment the appointment object containing appointment data as well as the updated customer rating
     */
    public void addCustomerRatingToDB(Appointment appointment) {

    }

}
