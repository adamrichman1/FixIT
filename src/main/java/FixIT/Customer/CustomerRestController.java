package FixIT.Customer;

import FixIT.Core.AppointmentDBManager;
import FixIT.Core.UserDBManager;
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
        return super.login(request, model, user);
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
     * Retrieves the DBManager of the subclass
     *
     * @return the DBManager in the subclass
     */
    @Override
    protected CustomerDBManager getDBManager() {
        return dbManager;
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
        return super.signUp(request, model, user);
    }

    /**
     * Called when a user (customer or staff) attempts to login to FixIT
     *
     * @return the login template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/customer/home")
    protected static String getCustomerHome() {
        logger.info("accessed");
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
     * Called when a user (customer or staff) attempts to login to FixIT
     *
     * @return the login template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/customer/create-appointment")
    protected static String getCreateAppointmentTemplate() {
        return "create-appointment";
    }

    /**
     * Retrieves a customer's rating
     *
     * @return the customer's rating
     */
    @RequestMapping(method= RequestMethod.GET, value="/customer/rating")
    protected static ResponseEntity getCustomerRating(HttpServletRequest request) {
        String username = request.getHeader("username");
        double rating = AppointmentDBManager.getCustomerRating(username);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
}
