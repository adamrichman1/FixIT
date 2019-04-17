package FixIT.Staff;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StaffDBManagerTests {

    @Before
    public void setup() {
        StaffDBManager.getInstance().dropUserTable();
        StaffDBManager.getInstance().createStaffTable();
    }

    @Test
    public void testInsertUserToDB() {
        String username = "sohelsarwar";
        Staff user = new Staff(username);

        //Test
        Staff s = StaffDBManager.getInstance().insertUserToDB(user);

        //Asserts
        assertEquals(s.getUsername(), username);
    }

    @Test
    public void testStaffAvailable() {
        String username = "sohelsarwar";
        Staff user = new Staff(username);

        //Test
        Staff s = StaffDBManager.getInstance().insertUserToDB(user);
        boolean result = StaffDBManager.getInstance().staffAvailable();

        //Asserts
        assertEquals(StaffDBManager.getInstance().staffAvailable(), result);
    }

    @Test
    public void testFindStaff() {
        String username = "sohelsarwar";
        Staff user = new Staff(username);

        //Test
        Staff s = StaffDBManager.getInstance().insertUserToDB(user);
        String staffMember = StaffDBManager.getInstance().findStaff();

        //Asserts
        assertEquals(StaffDBManager.getInstance().findStaff(), staffMember);
    }
}
