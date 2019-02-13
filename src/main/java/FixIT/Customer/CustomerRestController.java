package FixIT.Customer;

import FixIT.Core.UserRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * This class manages HTTP endpoints for FixIT customers
 */
@RestController
public class CustomerRestController extends UserRestController<Customer> {

    /**
     * Called when a customer attempts to login to FixIT
     *
     * @param request the HttpRequest entity containing header information
     * @param user the login form of the customer attempting to login
     * @return a ResponseEntity to the user
     */
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
    protected ResponseEntity signUp(HttpServletRequest request, Customer user) {
        // TODO - sign-up
        return null;
    }
}
