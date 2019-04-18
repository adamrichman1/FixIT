package FixIT.Staff;

import FixIT.Core.User;
import FixIT.Core.UserRestController;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StaffRestControllerTests {

    /**
     * Tests signUpFormInvalid() when the form is valid
     */
    @Test
    public void testSignUpFormInvalidFalse() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // Create valid customer
        Staff s = new Staff();
        s.setUsername("adam");
        s.setPassword("password");
        s.setEmail("gmail");
        s.setName("adam");
        s.setAddress("pgh");

        // Use reflection to make method accessible
        StaffRestController restController = new StaffRestController();
        Method m = UserRestController.class.getDeclaredMethod("signUpFormInvalid", User.class);
        m.setAccessible(true);

        // Assert form is valid
        assertFalse((boolean)m.invoke(restController, s));
    }

    /**
     * Tests signUpFormInvalid() when the form is valid
     */
    @Test
    public void testSignUpFormInvalidTrue() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // Create customer without an address
        Staff s = new Staff();
        s.setUsername("adam");
        s.setPassword("password");
        s.setEmail("gmail");
        s.setName("adam");

        // Use reflection to make method accessible
        StaffRestController restController = new StaffRestController();
        Method m = UserRestController.class.getDeclaredMethod("signUpFormInvalid", User.class);
        m.setAccessible(true);

        // Assert form is invalid
        assertTrue((boolean)m.invoke(restController, s));
    }
}
