package FixIT.Staff;

import FixIT.Core.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * This class represents a FixIT staff member
 */
@JsonIgnoreProperties(ignoreUnknown =true)
public class Staff extends User {
    private int yearsWorked;
    private ArrayList<String> strengths;

    public Staff(String username) {
        super(username);
    }

    public int getYearsWorked() {
        return yearsWorked;
    }

    public void setYearsWorked(int yearsWorked) {
        this.yearsWorked = yearsWorked;
    }

    public ArrayList<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(ArrayList<String> strengths) {
        this.strengths = strengths;
    }
}
