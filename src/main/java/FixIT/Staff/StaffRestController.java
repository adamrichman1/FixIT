package FixIT.Staff;

import FixIT.Core.UserRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * This class manages HTTP endpoints for FixIT staff members
 */
@Controller
public class StaffRestController extends UserRestController<Staff> {

    private static StaffDBManager dbManager = StaffDBManager.getInstance();

    /**
     * Called when a staff member attempts to login to FixIT
     *
     * @param request the HttpRequest entity containing header information
     * @param user the login form of the staff member attempting to login
     * @return a ResponseEntity to the user
     */
    @RequestMapping(method= RequestMethod.POST, value="/staff/login")
    protected @ResponseBody ResponseEntity login(HttpServletRequest request, Model model, @RequestBody Staff user) {
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
    protected @ResponseBody ResponseEntity signUp(HttpServletRequest request, Model model, @RequestBody Staff user) {
        // TODO - sign-up
        return null;
    }

    /**
     * Called when a staff member attempts to access the home
     *
     * @return the login template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/staff/home")
    protected static String getStaffHome() {
        return "staff-home";
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

    /**
     * Called when a staff member attempts to login to FixIT
     *
     * @return the login template to the user
     */
    @RequestMapping(method= RequestMethod.GET, value="/staff/login")
    protected static String getLoginTemplate() {
        return "staff-login";
    }
}
