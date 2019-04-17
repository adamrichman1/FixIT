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
    private static AppointmentDBManager appointmentDBManager;
    private static String appointmentTable = "appointments";
    private static Logger logger = LoggerFactory.getLogger(AppointmentDBManager.class);

    private AppointmentDBManager() {}

    /**
     * Singleton design pattern
     *
     * @return the sole instance of AppointmentDBManager
     */
    public static AppointmentDBManager getInstance() {
        if (appointmentDBManager == null) {
            appointmentDBManager = new AppointmentDBManager();
        }
        return appointmentDBManager;
    }

    public static void createAppointmentTable() {
        executeUpdate("CREATE TABLE IF NOT EXISTS " + appointmentTable +
                " (problem              TEXT        NOT NULL," +
                " customerUsername      TEXT        NOT NULL," +
                " staffUsername         TEXT        NOT NULL," +
                " appointmentTime       TEXT        NOT NULL," +
                " worklog               TEXT        NOT NULL," +
                " appointmentStatus     INT         NOT NULL," +
                " appointmentCost       REAL        NOT NULL," +
                " appointmentID SERIAL PRIMARY KEY  NOT NULL)");
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
    public Appointment findAppointment(long appointmentID) {
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
     * @param appointmentCost the cost of the appointment
     */
    Appointment insertAppointmentToDB(String problem, String customerUsername, String staffUsername,
                               String appointmentTime, List<String> worklog, int appointmentStatus,
                               BigDecimal appointmentCost) {
        String sql = "INSERT INTO " + appointmentTable + " (problem, customerUsername, staffUsername, " +
                "appointmentTime, worklog, appointmentStatus, appointmentCost) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING *";
        return populateAppointment(executeUpdate(sql, problem, customerUsername, staffUsername, appointmentTime, new JSONArray(worklog).toString(),
                appointmentStatus, appointmentCost));
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
}
