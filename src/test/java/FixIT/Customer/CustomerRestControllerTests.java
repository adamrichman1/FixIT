package FixIT.Customer;

import FixIT.Core.User;
import FixIT.Core.UserRestController;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomerRestControllerTests {

    /**
     * Tests signUpFormInvalid() when the form is valid
     */
    @Test
    public void testSignUpFormInvalidFalse() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // Create valid customer
        Customer c = new Customer();
        c.setUsername("adam");
        c.setPassword("password");
        c.setEmail("gmail");
        c.setName("adam");
        c.setAddress("pgh");

        // Use reflection to make method accessible
        CustomerRestController restController = new CustomerRestController();
        Method m = UserRestController.class.getDeclaredMethod("signUpFormInvalid", User.class);
        m.setAccessible(true);

        // Assert form is valid
        assertFalse((boolean)m.invoke(restController, c));
    }

    /**
     * Tests signUpFormInvalid() when the form is valid
     */
    @Test
    public void testSignUpFormInvalidTrue() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // Create customer without an address
        Customer c = new Customer();
        c.setUsername("adam");
        c.setPassword("password");
        c.setEmail("gmail");
        c.setName("adam");

        // Use reflection to make method accessible
        CustomerRestController restController = new CustomerRestController();
        Method m = UserRestController.class.getDeclaredMethod("signUpFormInvalid", User.class);
        m.setAccessible(true);

        // Assert form is invalid
        assertTrue((boolean)m.invoke(restController, c));
    }
}
