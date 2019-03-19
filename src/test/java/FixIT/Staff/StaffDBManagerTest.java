package FixIT.Staff;

import FixIT.Core.Appointment;

import java.util.ArrayList;

public class StaffDBManagerTest
{
    static final String url = "jdbc:postgresql://localhost:5432/";

    public static void main(String[] args)
    {
        StaffDBManager dbManager = new StaffDBManager();

        // create user info
        String username = "EricBaumann";
        String password = "heyitsapassword";
        String email = "eric@me.com";
        String name = "Eric Baumann";
        String address = "123 Drury Lane";
        double rating = 3.9;
        int yearsWorked = 10;

        // create strengths field
        ArrayList<String> strengths = new ArrayList<String>();
        strengths.add("Wireless Networks");
        strengths.add("Printer Setup");
        strengths.add("iPhone Screen Repair");

        // create appointment field
        ArrayList<Appointment> appts = new ArrayList<Appointment>();
        Appointment appt1 = new Appointment("1");
        Appointment appt2 = new Appointment("2");
        Appointment appt3 = new Appointment("3");
        appts.add(appt1);
        appts.add(appt2);
        appts.add(appt3);

        // make Staff object
        Staff user1 = new Staff(username);
        user1.setPassword(password);
        user1.setEmail(email);
        user1.setName(name);
        user1.setAddress(address);
        user1.setRating(rating);
        user1.setYearsWorked(yearsWorked);
        user1.setStrengths(strengths);

        // insert into DB
        dbManager.insertUserToDB(user1);

        // get the user profile
        if(dbManager.userExists(username))
        {
            Staff return_user = dbManager.getUserProfile(username);

            if(user1.getUsername().equals(return_user.getUsername()))
            {
                System.out.println("Username is incorrect");
            }
            if(user1.getPassword().equals(return_user.getPassword()))
            {
                System.out.println("password is incorrect");
            }
            if(user1.getEmail().equals(return_user.getEmail()))
            {
                System.out.println("email is incorrect");
            }
            if(user1.getName().equals(return_user.getName()))
            {
                System.out.println("name is incorrect");
            }
            if(user1.getAddress().equals(return_user.getAddress()))
            {
                System.out.println("address is incorrect");
            }
            if(user1.getAppointmentHistory() != return_user.getAppointmentHistory())
            {
                System.out.println("appointment history is incorrect");
            }
            if(user1.getRating() != return_user.getRating())
            {
                System.out.println("rating is incorrect");
            }
            if(user1.getYearsWorked() != return_user.getYearsWorked())
            {
                System.out.println("years worked is incorrect");
            }
            if(user1.getStrengths() != return_user.getStrengths())
            {
                System.out.println("strengths are incorrect");
            }
        }


    }
}
