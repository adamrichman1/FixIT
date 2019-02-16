package FixIT.Core;

import java.util.ArrayList;

/**
 * This class contains query functionality specific to Appointments
 */
public class AppointmentDBManager extends DBManager {
    private static String appointmentTable = "appointments";

    /**
     * Used to insert an appointment into the appointment table once requested by a customer
     *
     * @param appointment the appointment object containing appointment data to insert
     */
    public void insertAppointmentToDB(Appointment appointment) {

    }

    /**
     * Used so the user can view all of their appointments in the past, present, and future
     *
     * @param username the username of the user whose appointments are being queried for
     * @return an ArrayList of Appointment objects for the user
     */
    public ArrayList<Appointment> findAllAppointments(String username) {
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
