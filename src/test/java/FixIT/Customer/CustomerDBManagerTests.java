package FixIT.Customer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CustomerDBManagerTests {

    /**
     * Setup operations
     */
    @Before
    public void setup() {
        CustomerDBManager.getInstance().dropUserTable();
        CustomerDBManager.getInstance().createCustomerTable();
    }

    /**
     * Shutdown operations
     */
    @After
    public void teardown() {
        CustomerDBManager.getInstance().dropUserTable();
    }

    /**
     * Tests insertUserToDB()
     */
    @Test
    public void testInsertCustomerToDB() {
        Customer c = new Customer();
        c.setUsername("adam");
        c.setPassword("pass");
        c.setEmail("email");
        c.setName("adam");
        c.setAddress("address");
        c.setAppointmentHistory(new ArrayList<>());
        c.setCreditCard("creditCard");

        // Test
        Customer inserted = CustomerDBManager.getInstance().insertUserToDB(c);

        // Asserts
        assertEquals(c.getUsername(), inserted.getUsername());
        assertEquals(c.getPassword(), inserted.getPassword());
        assertEquals(c.getEmail(), inserted.getEmail());
        assertEquals(c.getAddress(), c.getAddress());
        assertEquals(c.getAppointmentHistory().size(), inserted.getAppointmentHistory().size());
        assertEquals(c.getCreditCard(), inserted.getCreditCard());
    }
}
