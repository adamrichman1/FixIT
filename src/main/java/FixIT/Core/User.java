package FixIT.Core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * This class represents a User in the FixIT system. Subclasses include Staff and Customer.
 */
@JsonIgnoreProperties(ignoreUnknown =true)
public abstract class User {
    private String username;
    private String password;
    private String email;
    private String name;
    private String address;
    private ArrayList<Appointment> appointmentHistory = new ArrayList<>();

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Appointment> getAppointmentHistory() {
        return appointmentHistory;
    }

    public void setAppointmentHistory(ArrayList<Appointment> appointmentHistory) {
        this.appointmentHistory = appointmentHistory;
    }
}
