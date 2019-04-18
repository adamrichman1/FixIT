package FixIT.Staff;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StaffDBManagerTests {

    /**
     * Setup operations
     */
    @Before
    public void setup() {
        StaffDBManager.getInstance().dropUserTable();
        StaffDBManager.getInstance().createStaffTable();
    }

    /**
     * Shutdown operations
     */
    @After
    public void teardown() {
        StaffDBManager.getInstance().dropUserTable();
    }

    /**
     * Tests insertUserToDB()
     */
    @Test
    public void testInsertCustomerToDB() {
        Staff s = new Staff();
        s.setUsername("adam");
        s.setPassword("pass");
        s.setEmail("email");
        s.setName("adam");
        s.setAddress("address");
        s.setAppointmentHistory(new ArrayList<>());

        // Test
        Staff inserted = StaffDBManager.getInstance().insertUserToDB(s);

        // Asserts
        assertEquals(s.getUsername(), inserted.getUsername());
        assertEquals(s.getEmail(), inserted.getEmail());
        assertEquals(s.getAddress(), s.getAddress());
        assertEquals(s.getAppointmentHistory().size(), inserted.getAppointmentHistory().size());
    }

    /**
     * Tests staffAvailable() when a staff-member is available
     */
    @Test
    public void testStaffAvailableTrue() {
        // Insert mock staff member to DB
        Staff s = new Staff();
        s.setUsername("adam");
        s.setPassword("pass");
        s.setEmail("email");
        s.setName("adam");
        s.setAddress("address");
        s.setAppointmentHistory(new ArrayList<>());
        StaffDBManager.getInstance().insertUserToDB(s);

        // Assert
        assertTrue(StaffDBManager.getInstance().staffAvailable());
    }

    /**
     * Tests staffAvailable() when a staff-member is NOT available
     */
    @Test
    public void testStaffAvailableFalse() {
        // Assert
        assertFalse(StaffDBManager.getInstance().staffAvailable());
    }

    @Test
    public void testFindStaff() {
        // Insert mock staff member to DB
        Staff s = new Staff();
        s.setUsername("adam");
        s.setPassword("pass");
        s.setEmail("email");
        s.setName("adam");
        s.setAddress("address");
        s.setAppointmentHistory(new ArrayList<>());
        StaffDBManager.getInstance().insertUserToDB(s);

        // Find a random staff member
        String staffUsername = StaffDBManager.getInstance().findStaff();

        // Asserts
        assertEquals(s.getUsername(), staffUsername);
    }
}


