package FixIT.Core;

import FixIT.Staff.StaffDBManager;

import java.util.List;

/**
 * This class manages all appointment logic
 */
public class AppointmentManager {

    private static AppointmentDBManager appointmentDBManager = new AppointmentDBManager();
    private static StaffDBManager staffDBManager = StaffDBManager.getInstance();

    /**
     * Creates a new appointment
     *
     * @param appointment the appointment to create
     */
    static void createAppointment(Appointment appointment) {
        appointmentDBManager.insertAppointmentToDB(
                appointment.getProblem(),
                appointment.getCustomerUsername(),
                appointment.getStaffUsername(),
                appointment.getAppointmentTime(),
                appointment.getWorkLog(),
                appointment.getAppointmentStatus(),
                appointment.getCustomerRating(),
                appointment.getStaffRating(),
                appointment.getAppointmentCost()
        );
    }

    /**
     * Determines if a staff member is available to accept an appointment
     *
     * @return true if there is a staff member available to take on an appointment, false otherwise
     */
    static boolean isStaffMemberAvailable(long appointmentTime) {
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
    public static List<Appointment> getCustomerAppointmentHistory(String customerUsername) {
        return appointmentDBManager.findCustomerAppointmentHistory(customerUsername);
    }

    /**
     * Finds appointment history for a staff member
     *
     * @param staffUsername the username of the staff member
     * @return the staff member's appointment history
     */
    public static List<Appointment> getStaffAppointmentHistory(String staffUsername) {
        return appointmentDBManager.findStaffAppointmentHistory(staffUsername);
    }
}
