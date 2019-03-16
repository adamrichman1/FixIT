package FixIT.Staff;

import FixIT.Core.User;

import java.util.ArrayList;

/**
 * This class represents a FixIT staff member
 */
public class Staff extends User {
    private int yearsWorked;
    private ArrayList<String> strengths;

    Staff(String username) {
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
