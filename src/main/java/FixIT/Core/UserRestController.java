package FixIT.Core;

import org.springframework.ui.Model;

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
     * @param model the model that allows for html templates to be customized
     * @param user the login form of the user attempting to login
     * @return a ResponseEntity to the user
     */
    protected abstract String login(HttpServletRequest request, Model model,  T user);

    /**
     * Requires subclasses of this class to implement a sign-up endpoint
     *
     * @param request the HttpRequest entity containing header information
     * @param model the model that allows for html templates to be customized
     * @param user the sign-up form of the user attempting to sign-up
     * @return a ResponseEntity to the user
     */
    protected abstract String signUp(HttpServletRequest request, Model model, T user);

    /**
     * Used by users to obtain a sign-up template (either customer or staff)
     *
     * @return the signup template to the user
     */
    protected abstract String getSignupTemplate();
}
