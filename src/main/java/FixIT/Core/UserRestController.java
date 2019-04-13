package FixIT.Core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class contains methods that are shared between StaffRestController and CustomerRestController. It also contains
 * abstract methods that represent required endpoints that must be implemented in subclasses.
 *
 * @param <T> the type of User object that passes through this class (either Customer or Staff)
 */
public abstract class UserRestController<T extends User> {

    private static Logger logger = LoggerFactory.getLogger(UserRestController.class);

    /**
     * Requires subclasses of this class to implement a login endpoint
     *
     * @param user the login form of the user attempting to login
     * @return a ResponseEntity to the user
     */
    protected ResponseEntity login(T user) {
        if (!getDBManager().userExists(user.getUsername())) {
            logger.warn("Invalid username");
            return new ResponseEntity<>("Invalid username", HttpStatus.BAD_REQUEST);
        }
        else if (!getDBManager().passwordValid(user.getUsername(), user.getPassword())){
            logger.warn("Invalid password");
            return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
        }
        else {
            logger.info("SUCCESS");
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    /**
     * Requires subclasses of this class to implement a sign-up endpoint
     *
     * @param user the sign-up form of the user attempting to sign-up
     * @return a ResponseEntity to the user
     */
    protected ResponseEntity signUp(T user) {
        logger.info("SignUp - Customer: " + user.toString());
        // Check validity of sign-up form
        if (signUpFormInvalid(user)) {
            logger.info("Invalid signup form");
            return new ResponseEntity<>("Invalid registration form", HttpStatus.BAD_REQUEST);
        }
        // Check if user already exists
        else if (getDBManager().userExists(user.getUsername())) {
            logger.info("Username already exists");
            return new ResponseEntity<>("Username already in use",  HttpStatus.BAD_REQUEST);
        }
        // Register user and return success
        else {
            logger.info("Success!");
            getDBManager().insertUserToDB(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    /**
     * Used to determine if a sign-up form is invalid
     *
     * @param user the user object passed up via the sign-up endpoint
     * @return true if the form contains an invalid field, false otherwise
     */
    private boolean signUpFormInvalid(T user) {
        return (user.getUsername() == null || user.getUsername().equals("")) ||
                user.getPassword() == null || user.getPassword().equals("") ||
                user.getEmail() == null || user.getEmail().equals("") ||
                user.getName() == null || user.getName().equals("") ||
                user.getAddress() == null || user.getAddress().equals("");
    }

    /**
     * Used by users to obtain a sign-up template (either customer or staff)
     *
     * @return the signup template to the user
     */
    protected abstract String getSignupTemplate();

    /**
     * Retrieves the DBManager of the subclass
     *
     * @return the DBManager in the subclass
     */
    protected abstract UserDBManager<T> getDBManager();
}
