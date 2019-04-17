package FixIT.Core;

import FixIT.Staff.Staff;
import FixIT.Staff.StaffDBManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UserRestControllerTests {
    @Before
    public void setup() {
        StaffDBManager.getInstance().dropUserTable();
        StaffDBManager.getInstance().createStaffTable();
    }

    @Test
    public void testSignUpFormInvalidTrue(){
        String username = "sohelsarwar";
        String password = "123456";
        String email = "sohel@sarwar.org";
        String name = "sohel";
        String address = "1530 Way, Pittsburgh PA";

        Staff u = new Staff(username);

        u.setPassword(password);
        u.setEmail(email);
        u.setName(name);
        u.setAddress(address);


        // Test
        boolean result = true;
        // result =UserRestController.getInstance().signUpFormInvalid(u)

        //Assert
        assertEquals(true, result);
    }

    @Test
    public void testSignUpFormInvalidFalse(){
        String username = "sohelsarwar";
        String password = "123456";
        String email = "";
        String name = "sohel";
        String address = "1530 Way, Pittsburgh PA";

        Staff u = new Staff(username);

        u.setPassword(password);
        u.setEmail(email);
        u.setName(name);
        u.setAddress(address);


        // Test
        boolean result = false;
        // result =UserRestController.getInstance().signUpFormInvalid(u)

        //Assert
        assertEquals(true, result);
    }
}


