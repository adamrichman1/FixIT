package FixIT.Customer;

import FixIT.Core.AppointmentDBManager;
import FixIT.Core.UserRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * @param user the login form of the customer attempting to login
     * @return a ResponseEntity to the user
     */
    @Override
    @RequestMapping(method= RequestMethod.POST, value="/customer/login")
    protected ResponseEntity login(@RequestBody Customer user) {
        return super.login(user);
    }

    /**
     * Called when a customer attempts to sign-up with FixIT
     *
     * @return the customer sign-up template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/customer/signup")
    public String getSignupTemplate() {
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
     * @param user the sign-up form of the user attempting to sign-up
     * @return a ResponseEntity to the user
     */
    @Override
    @RequestMapping(method= RequestMethod.POST, value="/customer/signup")
    protected ResponseEntity signUp(@RequestBody Customer user) {
        return super.signUp(user);
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
     * Called when a user attempts to create an appointment
     *
     * @return the create-appointment template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/customer/createAppointment")
    protected static String getCreateAppointmentTemplate() {
        return "create-appointment";
    }

    /**
     * Called when a user fails to create an appointment
     *
     * @return the appointment-error template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/appointment/error")
    protected static String getAppointmentErrorTemplate() {
        return "appointment-error";
    }

    /**
     * Called when a staff member views an appointment
     *
     * @return the appointment worklog template to the staff-member
     */
    @RequestMapping(method= RequestMethod.GET, value="/customer/appointment/summary")
    protected static String getWorklogTemplate(@RequestParam("appointmentID") long appointmentID, Model model) {
        model.addAttribute("appointment", AppointmentDBManager.getInstance().findAppointment(appointmentID));
        return "appointment-summary";
    }
}
