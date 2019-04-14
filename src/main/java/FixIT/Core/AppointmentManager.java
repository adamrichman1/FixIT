package FixIT.Core;

import FixIT.Staff.StaffDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages all appointment logic
 */
class AppointmentManager {

    private static AppointmentDBManager appointmentDBManager = new AppointmentDBManager();
    private static StaffDBManager staffDBManager = StaffDBManager.getInstance();
    private static Logger logger = LoggerFactory.getLogger(AppointmentManager.class);

    /**
     * Creates a new appointment
     *
     * @param appointment the appointment to create
     */
    static void createAppointment(Appointment appointment) {
        logger.info(appointment.toString());
        appointmentDBManager.insertAppointmentToDB(
                appointment.getProblem(),
                appointment.getCustomerUsername(),
                appointment.getStaffUsername(),
                appointment.getAppointmentTime(),
                new ArrayList<>(),
                0,
                0,
                0,
                appointment.getAppointmentCost()
        );
    }

    /**
     * Determines if a staff member is available to accept an appointment
     *
     * @return true if there is a staff member available to take on an appointment, false otherwise
     */
    static boolean isStaffMemberAvailable() {
        return staffDBManager.staffAvailable();
    }

    /**
     * Finds a staff member to assign to an appointment
     *
     * @return the username of the staff member
     */
    static String findStaffMember() {
        return staffDBManager.findStaff();
    }

    /**
     * Finds appointment history for a customer
     *
     * @param customerUsername the username of the customer
     * @return the customer's appointment history
     */
    static List<Appointment> getCustomerAppointmentHistory(String customerUsername) {
        return appointmentDBManager.findCustomerAppointmentHistory(customerUsername);
    }

    /**
     * Finds appointment history for a staff member
     *
     * @param staffUsername the username of the staff member
     * @return the staff member's appointment history
     */
    static List<Appointment> getStaffAppointmentHistory(String staffUsername) {
        return appointmentDBManager.findStaffAppointmentHistory(staffUsername);
    }

    /**
     * Updates the appointment status in the DB
     *
     * @param appointmentID the ID of the appointment to update
     * @param appointmentStatus the new appointment status
     */
    static void updateAppointmentStatus(long appointmentID, int appointmentStatus) {
        appointmentDBManager.updateAppointmentStatus(appointmentID, appointmentStatus);
    }

    /**
     * Updates the worklog for an appointment in the DB
     *
     * @param appointmentID the ID of the appointment to update
     * @param worklog the updated worklog for the appointment
     */
    static void updateWorklog(long appointmentID, List<String> worklog) {
        appointmentDBManager.updateAppointmentWorklog(appointmentID, worklog);
    }

    /**
     * Updates the customer rating for an appointment
     *
     * @param appointmentID the ID of the appointment to update
     * @param customerRating the rating for the customer
     */
    static void addCustomerRating(long appointmentID, int customerRating) {
        appointmentDBManager.addCustomerRatingToDB(appointmentID, customerRating);
    }

    /**
     * Updates the staff rating for an appointment
     *
     * @param appointmentID the ID of the appointment to update
     * @param staffRating the rating for the staff member
     */
    static void addStaffRating(long appointmentID, int staffRating) {
        appointmentDBManager.addStaffRatingToDB(appointmentID, staffRating);
    }
}
