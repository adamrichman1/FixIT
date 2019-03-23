package FixIT.Staff;

import FixIT.Core.UserRestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * This class manages HTTP endpoints for FixIT staff members
 */
@RestController
public class StaffRestController extends UserRestController<Staff> {

    /**
     * Called when a staff member attempts to login to FixIT
     *
     * @param request the HttpRequest entity containing header information
     * @param user the login form of the staff member attempting to login
     * @return a ResponseEntity to the user
     */
    @RequestMapping(method= RequestMethod.POST, value="/staff/login")
    protected @ResponseBody String login(HttpServletRequest request, Model model, Staff user) {
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
    @RequestMapping(method= RequestMethod.POST, value="/staff/signUp")
    protected @ResponseBody String signUp(HttpServletRequest request, Model model, Staff user) {
        // TODO - sign-up
        return null;
    }
}
