package FixIT.Staff;

import FixIT.Core.UserRestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * This class manages HTTP endpoints for FixIT staff members
 */
@Controller
public class StaffRestController extends UserRestController<Staff> {

    /**
     * Called when a staff member attempts to login to FixIT
     *
     * @param request the HttpRequest entity containing header information
     * @param user the login form of the staff member attempting to login
     * @return a ResponseEntity to the user
     */
    @RequestMapping(method= RequestMethod.POST, value="/staff/login")
    protected @ResponseBody String login(HttpServletRequest request, Model model, @RequestBody Staff user) {
        String username = request.getHeader("username");
        // TODO - login
        return null;
    }

    /**
     * Called when a staff member attempts to sign-up with FixIT
     *
     * @param request the HttpRequest entity containing header information
     * @param user the sign-up form of the user attempting to sign-up
     * @return a ResponseEntity to the user
     */
    @Override
    @RequestMapping(method= RequestMethod.POST, value="/staff/signup")
    protected @ResponseBody String signUp(HttpServletRequest request, Model model, @RequestBody Staff user) {
        // TODO - sign-up
        return null;
    }

    /**
     * Called when a staff member attempts to sign-up with FixIT
     *
     * @return the staff sign-up template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/staff/signup")
    protected String getSignupTemplate() {
        return "signup-staff";
    }
}
