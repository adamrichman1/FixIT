package FixIT.Core;

import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

/**
 * This class contains methods that are shared between StaffRestController and CustomerRestController. It also contains
 * abstract methods that represent required endpoints that must be implemented in subclasses.
 *
 * @param <T> the type of User object that passes through this class (either Customer or Staff)
 */
public abstract class UserRestController<T extends User> {

    /**
     * Requires subclasses of this class to implement a login endpoint
     *
     * @param request the HttpRequest entity containing header information
     * @param user the login form of the user attempting to login
     * @return a ResponseEntity to the user
     */
    protected abstract ResponseEntity login(HttpServletRequest request, T user);

    /**
     * Requires subclasses of this class to implement a sign-up endpoint
     *
     * @param request the HttpRequest entity containing header information
     * @param user the sign-up form of the user attempting to sign-up
     * @return a ResponseEntity to the user
     */
    protected abstract ResponseEntity signUp(HttpServletRequest request, T user);
}
