package FixIT.Customer;

import FixIT.Core.AppointmentManager;
import FixIT.Core.UserRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * This class manages HTTP endpoints for FixIT customers
 */
@Controller
public class CustomerRestController extends UserRestController<Customer> {

    private static CustomerDBManager dbManager = CustomerDBManager.getInstance();
    private static Logger logger = LoggerFactory.getLogger(CustomerRestController.class);

    /**
     * Called when a customer attempts to login to FixIT
     *
     * @param request the HttpRequest entity containing header information
     * @param model the model that allows for html templates to be customized
     * @param user the login form of the customer attempting to login
     * @return a ResponseEntity to the user
     */
    @Override
    @RequestMapping(method= RequestMethod.POST, value="/customer/login")
    protected ResponseEntity login(HttpServletRequest request, Model model, @RequestBody Customer user) {
        logger.info("LOGIN");
        if (!dbManager.userExists(user.getUsername())) {
            logger.warn("Invalid username");
            return new ResponseEntity<>("Invalid username", HttpStatus.BAD_REQUEST);
        }
        else if (!dbManager.passwordValid(user.getUsername(), user.getPassword())){
            logger.warn("Invalid password");
            return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
        }
        else {
            logger.info("SUCCESS");
            // TODO return cookie?
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    /**
     * Called when a customer attempts to sign-up with FixIT
     *
     * @return the customer sign-up template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/customer/signup")
    protected String getSignupTemplate() {
        return "signup-customer";
    }

    /**
     * Called when a customer attempts to sign-up with FixIT
     *
     * @param request the HttpRequest entity containing header information
     * @param model the model that allows for html templates to be customized
     * @param user the sign-up form of the user attempting to sign-up
     * @return a ResponseEntity to the user
     */
    @Override
    @RequestMapping(method= RequestMethod.POST, value="/customer/signup")
    protected ResponseEntity signUp(HttpServletRequest request, Model model, @RequestBody Customer user) {
        logger.info("SignUp - Customer: " + user.toString());
        // Check validity of sign-up form
        if (signUpFormInvalid(user)) {
            logger.info("Invalid signup form");
            return new ResponseEntity<>("Invalid registration form", HttpStatus.BAD_REQUEST);
        }
        // Check if user already exists
        else if (dbManager.userExists(user.getUsername())) {
            logger.info("Username already exists");
            model.addAttribute("errorMsg", "Username already in use");
            return new ResponseEntity<>("Username already in use",  HttpStatus.BAD_REQUEST);
        }
        // Register user and return success
        else {
            logger.info("Success!");
            dbManager.insertUserToDB(user.getUsername(), user.getPassword(), user.getEmail(), user.getName(),
                    user.getAddress(), user.getAppointmentHistory(), user.getCreditCard());
            // TODO return cookie
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Called when a user (customer or staff) attempts to login to FixIT
     *
     * @return the login template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/customer/home")
    protected static String getCustomerHome(HttpServletRequest request, Model model) {
        String username = request.getHeader("username");
        model.addAttribute("appointmentHistory", AppointmentManager.getStaffAppointmentHistory(username));
        return "customer-home";
    }

    /**
     * Called when a user (customer or staff) attempts to login to FixIT
     *
     * @return the login template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/customer/login")
    protected static String getLoginTemplate() {
        return "customer-login";
    }

    /**
     * Used to determine if a sign-up form is invalid
     *
     * @param user the user object passed up via the sign-up endpoint
     * @return true if the form contains an invalid field, false otherwise
     */
    private boolean signUpFormInvalid(Customer user) {
        return (user.getUsername() == null || user.getUsername().equals("")) ||
                user.getPassword() == null || user.getPassword().equals("") ||
                user.getEmail() == null || user.getEmail().equals("") ||
                user.getName() == null || user.getName().equals("") ||
                user.getAddress() == null || user.getAddress().equals("") ||
                user.getCreditCard() == null || user.getCreditCard().equals("");
    }
}
