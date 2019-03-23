package FixIT.Customer;

import FixIT.Core.UserRestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * This class manages HTTP endpoints for FixIT customers
 */
@Controller
@RequestMapping("/")
public class CustomerRestController extends UserRestController<Customer> {

    private static CustomerDBManager dbManager = new CustomerDBManager();

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
    protected String login(HttpServletRequest request, Model model, @RequestBody Customer user) {
        if (!dbManager.userExists(user.getUsername())) {
            model.addAttribute("errorMsg", "Invalid username");
            return "login";
        }
        else if (!dbManager.passwordValid(user.getUsername(), user.getPassword())){
            model.addAttribute("errorMsg", "Invalid password");
            return "login";
        }
        else {
            // TODO return cookie?
            return "redirect:index";
        }
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
    @RequestMapping(method= RequestMethod.POST, value="/customer/signUp")
    protected String signUp(HttpServletRequest request, Model model, @RequestBody Customer user) {
        // Check validity of sign-up form
        if (signUpFormInvalid(user)) {
            model.addAttribute("errorMsg", "Invalid registration form");
            return "signup-error";
        }
        // Check if user already exists
        else if (dbManager.userExists(user.getUsername())) {
            model.addAttribute("errorMsg", "Username already in use");
            return "signup-error";
        }
        // Register user and return success
        else {
            dbManager.insertUserToDB(user.getUsername(), user.getPassword(), user.getEmail(), user.getName(),
                    user.getAddress(), user.getAppointmentHistory(), user.getCreditCard());
            // TODO return cookie?
            return "signup-success";
        }
    }

    /**
     * Used to determine if a sign-up form is invalid
     *
     * @param user the user object passed up via the sign-up endpoint
     * @return true if the form contains an invalid field, false otherwise
     */
    private boolean signUpFormInvalid(Customer user) {
        return user.getUsername() == null ||
                user.getPassword() == null ||
                user.getEmail() == null ||
                user.getName() == null ||
                user.getAddress() == null ||
                user.getCreditCard() == null;
    }
}
