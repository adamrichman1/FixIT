package FixIT.Staff;

import FixIT.Application;
import FixIT.Core.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * This class represents a FixIT staff member
 */
@JsonIgnoreProperties(ignoreUnknown =true)
public class Staff extends User {
    public Staff() {}

    public Staff(String username) {
        super(username);
    }

    public String toString() {
        return Application.toString(this);
    }
}
