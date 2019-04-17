package FixIT.Core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * This class manages HTTP endpoints for appointment-related functionality (e.g. createAppointment)
 */
@Controller
public class AppointmentRestController {

    private static Logger logger = LoggerFactory.getLogger(AppointmentRestController.class);

    /**
     * Called when a customer tries to create an appointment
     *
     * @param request the HttpRequest entity containing header information
     * @param appointment the appointment object containing appointment data requested by a customer
     * @return a ResponseEntity to the user containing the result of the request in the body
     */
    @RequestMapping(method= RequestMethod.POST, value="/createAppointment", headers="Accept=application/json")
    @ResponseBody ResponseEntity createAppointment(HttpServletRequest request, @RequestBody Appointment appointment) {
        if (!AppointmentManager.isStaffMemberAvailable()) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        appointment.setCustomerUsername(request.getHeader("username"));
        appointment.setStaffUsername(AppointmentManager.findStaffMember());
        AppointmentManager.createAppointment(appointment);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Used to retrieve a user's appointment history
     *
     * @param request the HttpRequest entity containing header information and the username of the requesting user
     * @return a ResponseEntity to the user containing the appointment history in the response body
     */
    @RequestMapping(method= RequestMethod.GET, value="/customer/getAppointmentHistory", headers="Accept=application/json")
    @ResponseBody ResponseEntity getCustomerAppointmentHistory(HttpServletRequest request) {
        String username = request.getHeader("username");
        logger.info("Finding appointment history for customer: " + username);
        List<Appointment> appointments = AppointmentManager.getCustomerAppointmentHistory(username);
        logger.info("Appointment history: " + Arrays.toString(appointments.toArray()));
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    /**
     * Used to retrieve a user's appointment history
     *
     * @param request the HttpRequest entity containing header information and the username of the requesting user
     * @return a ResponseEntity to the user containing the appointment history in the response body
     */
    @RequestMapping(method= RequestMethod.GET, value="/staff/getAppointmentHistory", headers="Accept=application/json")
    @ResponseBody ResponseEntity getStaffAppointmentHistory(HttpServletRequest request) {
        String username = request.getHeader("username");
        logger.info("Finding appointment history for staff: " + username);
        List<Appointment> appointments = AppointmentManager.getStaffAppointmentHistory(username);
        logger.info("Appointment history: " + Arrays.toString(appointments.toArray()));
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    /**
     * Used when a FixIT staff member updates the work-log for an appointment
     *
     * @param appointment the appointment object containing an updated appointment work-log
     * @return a ResponseEntity to the user containing confirmation of the work-log update
     */
    @RequestMapping(method= RequestMethod.PUT, value="/updateWorklog", headers="Accept=application/json")
    @ResponseBody ResponseEntity updateAppointmentWorklog(@RequestBody Appointment appointment) {
        logger.info(appointment.toString());
        AppointmentManager.updateWorklog(appointment.getAppointmentID(), appointment.getWorkLog());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Used by FixIT staff members to update an appointment's status (0=not-started, 1=in-progress, 2=complete)
     *
     * @param appointment the appointment object containing an updated appointment status
     * @return a ResponseEntity to the user containing confirmation of status update
     */
    @RequestMapping(method= RequestMethod.PUT, value="/updateAppointmentStatus", headers="Accept=application/json")
    @ResponseBody ResponseEntity updateAppointmentStatus(@RequestBody Appointment appointment) {
        logger.info(appointment.toString());
        AppointmentManager.updateAppointmentStatus(appointment.getAppointmentID(), appointment.getAppointmentStatus());
        return new ResponseEntity(HttpStatus.OK);
    }
}
