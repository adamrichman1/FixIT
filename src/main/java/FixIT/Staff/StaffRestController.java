package FixIT.Staff;

import FixIT.Core.AppointmentDBManager;
import FixIT.Core.UserRestController;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This class manages HTTP endpoints for FixIT staff members
 */
@Controller
public class StaffRestController extends UserRestController<Staff> {

    private static StaffDBManager dbManager = StaffDBManager.getInstance();

    /**
     * Called when a staff member attempts to login to FixIT
     *
     * @param user the login form of the staff member attempting to login
     * @return a ResponseEntity to the user
     */
    @RequestMapping(method= RequestMethod.POST, value="/staff/login")
    protected @ResponseBody ResponseEntity login(@RequestBody Staff user) {
        return super.login(user);
    }

    /**
     * Called when a staff member attempts to sign-up with FixIT
     *
     * @param user the sign-up form of the user attempting to sign-up
     * @return a ResponseEntity to the user
     */
    @Override
    @RequestMapping(method= RequestMethod.POST, value="/staff/signup")
    protected @ResponseBody ResponseEntity signUp(@RequestBody Staff user) {
        return super.signUp(user);
    }

    /**
     * Retrieves the DBManager of the subclass
     *
     * @return the DBManager in the subclass
     */
    @Override
    protected StaffDBManager getDBManager() {
        return dbManager;
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
    public String getSignupTemplate() {
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

    /**
     * Called when a staff member views an appointment
     *
     * @return the appointment worklog template to the staff-member
     */
    @RequestMapping(method= RequestMethod.GET, value="/staff/appointment/worklog")
    protected static String getWorklogTemplate(@RequestParam("appointmentID") long appointmentID, Model model) {
        model.addAttribute("appointment", AppointmentDBManager.getInstance().findAppointment(appointmentID));
        return "staff-worklog";
    }
}
