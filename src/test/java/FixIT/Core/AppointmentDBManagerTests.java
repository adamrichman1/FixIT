package FixIT.Core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AppointmentDBManagerTests {

    /**
     * Setup operations
     */
    @Before
    public void setup() {
        AppointmentDBManager.dropAppointmentTable();
        AppointmentDBManager.createAppointmentTable();
    }

    /**
     * Shutdown operations
     */
    @After
    public void teardown() {
        AppointmentDBManager.dropAppointmentTable();
    }

    /**
     * Tests insertAppointmentToDB()
     */
    @Test
    public void testInsertAppointment() {
        String problem = "Computer not working";
        String customerUser = "adam";
        String staffUser = "jeff";
        String time = "5pm";
        int appointmentStatus = 0;
        BigDecimal cost = new BigDecimal(5);

        // Test
        Appointment a = AppointmentDBManager.getInstance().insertAppointmentToDB(problem, customerUser, staffUser, time,
                new ArrayList<>(), appointmentStatus, cost);

        // Asserts
        assertEquals(problem, a.getProblem());
        assertEquals(customerUser, a.getCustomerUsername());
        assertEquals(staffUser, a.getStaffUsername());
        assertEquals(time, a.getAppointmentTime());
        assertEquals(0, a.getWorkLog().size());
        assertEquals(appointmentStatus, a.getAppointmentStatus());
        assertEquals(cost, a.getAppointmentCost());
    }

    /**
     * Tests findCustomerAppointmentHistory()
     */
    @Test
    public void testFindCustomerAppointmentHistory() {
        // Insert first appointment for adam
        String problem = "Computer not working";
        String customerUser = "adam";
        String staffUser = "jeff";
        String time = "5pm";
        int appointmentStatus = 0;
        BigDecimal cost = new BigDecimal(5);
        AppointmentDBManager.getInstance().insertAppointmentToDB(problem, customerUser, staffUser, time,
                new ArrayList<>(), appointmentStatus, cost);

        // Insert second appointment for adam
        String problem2 = "Phone not working";
        String customerUser2 = "adam";
        String staffUser2 = "jeff";
        String time2 = "5pm";
        int appointmentStatus2 = 0;
        BigDecimal cost2 = new BigDecimal(5);
        AppointmentDBManager.getInstance().insertAppointmentToDB(problem2, customerUser2, staffUser2, time2,
                new ArrayList<>(), appointmentStatus2, cost2);

        // Insert third appointment for a different user
        AppointmentDBManager.getInstance().insertAppointmentToDB(problem2, "john", staffUser2, time2,
                new ArrayList<>(), appointmentStatus2, cost2);

        // Test
        List<Appointment> appointmentHistory = AppointmentDBManager.getInstance().findCustomerAppointmentHistory("adam");

        // Asserts
        assertEquals(problem, appointmentHistory.get(1).getProblem());
        assertEquals(problem2, appointmentHistory.get(0).getProblem());
        assertEquals(2, appointmentHistory.size());
    }

    /**
     * Tests findStaffAppointmentHistory()
     */
    @Test
    public void testFindStaffAppointmentHistory() {
        // Insert first appointment for jeff
        String problem = "Computer not working";
        String customerUser = "adam";
        String staffUser = "jeff";
        String time = "5pm";
        int appointmentStatus = 0;
        BigDecimal cost = new BigDecimal(5);
        AppointmentDBManager.getInstance().insertAppointmentToDB(problem, customerUser, staffUser, time,
                new ArrayList<>(), appointmentStatus, cost);

        // Insert second appointment for jeff
        String problem2 = "Phone not working";
        String customerUser2 = "adam";
        String staffUser2 = "jeff";
        String time2 = "5pm";
        int appointmentStatus2 = 0;
        BigDecimal cost2 = new BigDecimal(5);
        AppointmentDBManager.getInstance().insertAppointmentToDB(problem2, customerUser2, staffUser2, time2,
                new ArrayList<>(), appointmentStatus2, cost2);

        // Insert third appointment for a different staff member
        AppointmentDBManager.getInstance().insertAppointmentToDB(problem2, customerUser2, "chris", time2,
                new ArrayList<>(), appointmentStatus2, cost2);

        // Test
        List<Appointment> appointmentHistory = AppointmentDBManager.getInstance().findStaffAppointmentHistory("jeff");

        // Asserts
        assertEquals(problem, appointmentHistory.get(1).getProblem());
        assertEquals(problem2, appointmentHistory.get(0).getProblem());
        assertEquals(2, appointmentHistory.size());
    }

    /**
     * Tests updateAppointmentStatus
     */
    @Test
    public void testUpdateAppointmentStatus() {
        // Insert appointment
        String problem = "Computer not working";
        String customerUser = "adam";
        String staffUser = "jeff";
        String time = "5pm";
        int appointmentStatus = 0;
        BigDecimal cost = new BigDecimal(5);
        Appointment a = AppointmentDBManager.getInstance().insertAppointmentToDB(problem, customerUser, staffUser, time,
                new ArrayList<>(), appointmentStatus, cost);

        // Test - update appointment status to 1
        AppointmentDBManager.getInstance().updateAppointmentStatus(a.getAppointmentID(), 1);

        // Retrieve updated appointment
        Appointment updated = AppointmentDBManager.getInstance().findAppointment(a.getAppointmentID());

        // Asserts
        assertEquals(1, updated.getAppointmentStatus());
        assertEquals(problem, updated.getProblem());
    }

    /**
     * Tests updateAppointmentWorklog()
     */
    @Test
    public void testUpdateAppointmentWorklog() {
        // Insert appointment
        String problem = "Computer not working";
        String customerUser = "adam";
        String staffUser = "jeff";
        String time = "5pm";
        int appointmentStatus = 0;
        BigDecimal cost = new BigDecimal(5);
        Appointment a = AppointmentDBManager.getInstance().insertAppointmentToDB(problem, customerUser, staffUser, time,
                new ArrayList<>(), appointmentStatus, cost);

        // Test - update worklog
        ArrayList<String> updatedWorklog = new ArrayList<>();
        updatedWorklog.add("test1");
        AppointmentDBManager.getInstance().updateAppointmentWorklog(a.getAppointmentID(), updatedWorklog);

        // Retrieve updated appointment
        Appointment updated = AppointmentDBManager.getInstance().findAppointment(a.getAppointmentID());

        // Asserts
        assertEquals(1, updated.getWorkLog().size());
        assertEquals(updatedWorklog.get(0), updated.getWorkLog().get(0));
    }
}
