package FixIT.Customer;

import FixIT.Core.UserRestController;
import org.springframework.http.ResponseEntity;
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

    /**
     * Called when a customer attempts to login to FixIT
     *
     * @param request the HttpRequest entity containing header information
     * @param user the login form of the customer attempting to login
     * @return a ResponseEntity to the user
     */
    @Override
    @RequestMapping(method= RequestMethod.POST, value="/customer/login", headers="Accept=application/json")
    protected @ResponseBody ResponseEntity login(HttpServletRequest request, @RequestBody Customer user) {
        String username = request.getHeader("username");
        // TODO - login
        return null;
    }

    /**
     * Called when a customer attempts to sign-up with FixIT
     *
     * @param request the HttpRequest entity containing header information
     * @param user the sign-up form of the user attempting to sign-up
     * @return a ResponseEntity to the user
     */
    @Override
    @RequestMapping(method= RequestMethod.POST, value="/customer/signUp", headers="Accept=application/json")
    protected @ResponseBody ResponseEntity signUp(HttpServletRequest request, @RequestBody Customer user) {
        // TODO - sign-up
        return null;
    }

    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public String greet(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
