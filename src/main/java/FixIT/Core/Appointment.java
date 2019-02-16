package FixIT.Core;

import FixIT.Customer.Customer;
import FixIT.Staff.Staff;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * This class represents an appointment between a FixIT customer and FixIT staff member
 */
class Appointment {
    private String appiontmentID;
    private Customer customer;
    private Staff staff;
    private long appointmentTime;
    private ArrayList<String> workLog;
    private int appointmentStatus;
    private int staffRating;
    private int customerRating;
    private BigDecimal appointmentCost;

    public String getAppiontmentID() {
        return appiontmentID;
    }

    public void setAppiontmentID(String appiontmentID) {
        this.appiontmentID = appiontmentID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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
}
