package FixIT.Staff;

import FixIT.Core.UserDBManager;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * This class contains database/query functionality specific to FixIT staff
 */
public class StaffDBManager extends UserDBManager<Staff> {

    protected StaffDBManager() {
        super("staff");
    }

    /**
     * Used to insert a new staff member into the database
     *
     * @param user the staff member to insert into the database
     */
    @Override
    protected void insertUserToDB(Staff user)
    {
        // check if the username is taken
        if(!userExists(user.getUsername()))
        {
            // create query string
            String sql = "INSERT INTO " + userTable + " (username, password, email, name, address, " +
                    "appointmentHistory, rating, yearsWorked, strengths) VALUES(?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?) RETURNING *";

            // execute update
            executeUpdate(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getName(),
                    user.getAddress(), user.getAppointmentHistory(), user.getRating(), user.getYearsWorked(),
                    user.getStrengths());
        }
    }

    /**
     * Used to check if a staff member already exists before inserting them into the database table during registration
     *
     * @param username the username to query for
     * @return true if the username exists in the DB, false otherwise
     */
    @Override
    protected boolean userExists(String username)
    {
        // create sql string
        String sql = "SELECT * FROM " + userTable + " WHERE username = (username) VALUES(?)";

        // execute query
        ResultSet rs = executeQuery(sql, username);

        try
        {
            // if there's anything in the result set, the user exists
            if(rs.next())
            {
                return true;
            }
        }catch (Exception e)
        {
            return false;
        }

        // user does not exist
        return false;
    }

    /**
     * Used to get information about the user for the user's profile page
     *
     * @param username the username of the staff member whose profile should be queried for
     * @return a Staff object containing the staff member's profile
     */
    @Override
    protected Staff getUserProfile(String username)
    {
        // only continue if the user exists
        if(userExists(username))
        {
            // create sql string
            String sql = "SELECT * FROM " + userTable + " WHERE username = (username) VALUES(?)";

            // execute query
            ResultSet rs = executeQuery(sql, username);

            try
            {
                if(rs.next())
                {
                    // set the easy stuff
                    Staff user = new Staff(username);
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setName(rs.getString("name"));
                    user.setAddress(rs.getString("address"));
                    user.setRating(rs.getDouble("rating"));
                    user.setYearsWorked(rs.getInt("yearsWorked"));

                    // type checking (probably excessive but ¯\_(ツ)_/¯ )
                    Object obj = rs.getObject("appointmentHistory");
                    if(obj instanceof ArrayList)
                    {
                        if(!((ArrayList)obj).isEmpty())
                        {
                            // can't typecheck the way i would like because Appointment is in a diff package
                            /*if(((ArrayList)obj).get(0) instanceof Appointment)
                            {
                                user.setAppointmentHistory((ArrayList<Appointment>)obj);
                            }*/

                            user.setAppointmentHistory((ArrayList)obj);
                        }
                    }

                    // type checking (probably excessive but ¯\_(ツ)_/¯ )
                    obj = rs.getObject("strengths");
                    if(obj instanceof ArrayList)
                    {
                        if(!((ArrayList)obj).isEmpty())
                        {
                            if(((ArrayList)obj).get(0) instanceof String)
                            {
                                user.setStrengths((ArrayList<String>)obj);
                            }
                        }
                    }
                }
            }catch (Exception e)
            {
                return null;
            }
        }

        return null;
    }
}
