package FixIT.Core;

import FixIT.Application;
import FixIT.Staff.Staff;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * This class represents an appointment between a FixIT customer and FixIT staffUsername member
 */
public class Appointment {
    private String problem;
    private long appointmentID;
    private String customerUsername;
    private String staffUsername;
    private long appointmentTime;
    private ArrayList<String> workLog;
    private int appointmentStatus;
    private int staffRating;
    private int customerRating;
    private BigDecimal appointmentCost;

    // Appointment status values
    private static final int REQUESTED = 0;
    private static final int ACCEPTED = 1;
    private static final int COMPLETED = 2;
    private static final int CANCELLED = 3;

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getStaffUsername() {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }

    public long getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(long appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public ArrayList<String> getWorkLog() {
        return workLog;
    }

    public void setWorkLog(ArrayList<String> workLog) {
        this.workLog = workLog;
    }

    public int getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(int appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public int getStaffRating() {
        return staffRating;
    }

    public void setStaffRating(int staffRating) {
        this.staffRating = staffRating;
    }

    public int getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(int customerRating) {
        this.customerRating = customerRating;
    }

    public BigDecimal getAppointmentCost() {
        return appointmentCost;
    }

    public void setAppointmentCost(BigDecimal appointmentCost) {
        this.appointmentCost = appointmentCost;
    }

    public String toString() {
        return Application.toString(this);
    }
}
