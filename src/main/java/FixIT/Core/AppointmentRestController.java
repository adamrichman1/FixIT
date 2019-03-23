package FixIT.Core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * This class manages HTTP endpoints for appointment-related functionality (e.g. createAppointment)
 */
@RestController
public class AppointmentRestController {

    private AppointmentDBManager appointmentDBManager = new AppointmentDBManager();

    /**
     * Called when a customer tries to create an appointment
     *
     * @param request the HttpRequest entity containing header information
     * @param appointment the appointment object containing appointment data requested by a customer
     * @return a ResponseEntity to the user containing the result of the request in the body
     */
    @RequestMapping(method= RequestMethod.POST, value="/createAppointment", headers="Accept=application/json")
    @ResponseBody ResponseEntity createAppointment(HttpServletRequest request, Appointment appointment) {
        String username = request.getHeader("username");

        // TODO create appointment
        return null;
    }

    /**
     * Used to retrieve a user's appointment history
     *
     * @param request the HttpRequest entity containing header information and the username of the requesting user
     * @return a ResponseEntity to the user containing the appointment history in the response body
     */
    @RequestMapping(method= RequestMethod.GET, value="/getAppointmentHistory", headers="Accept=application/json")
    @ResponseBody ResponseEntity getAppointmentHistory(HttpServletRequest request) {
        String username = request.getHeader("username");
        // TODO find appointment history
        return null;
    }

    /**
     * Used when a FixIT staff member updates the work-log for an appointment
     *
     * @param request the HttpRequest entity containing header information and the username of the requesting user
     * @param appointment the appointment object containing an updated appointment work-log
     * @return a ResponseEntity to the user containing confirmation of the work-log update
     */
    @RequestMapping(method= RequestMethod.POST, value="/updateWorklog", headers="Accept=application/json")
    @ResponseBody ResponseEntity updateAppointmentWorklog(HttpServletRequest request, Appointment appointment) {
        String username = request.getHeader("username");
        // TODO update appointment worklog
        return null;
    }

    /**
     * Used to update the appointment time when requested by FixIT customers
     *
     * @param request the HttpRequest entity containing header information and the username of the requesting user
     * @param appointment the appointment object containing an updated appointment time
     * @return a ResponseEntity to the user containing confirmation of the time update
     */
    @RequestMapping(method= RequestMethod.POST, value="/updateAppointmentTime", headers="Accept=application/json")
    @ResponseBody ResponseEntity updateAppointmentTime(HttpServletRequest request, Appointment appointment) {
        String username = request.getHeader("username");
        // TODO update appointment time
        return null;
    }

    /**
     * Used by FixIT staff members to update an appointment's status (0=not-started, 1=in-progress, 2=complete)
     *
     * @param request the HttpRequest entity containing header information and the username of the requesting user
     * @param appointment the appointment object containing an updated appointment status
     * @return a ResponseEntity to the user containing confirmation of status update
     */
    @RequestMapping(method= RequestMethod.POST, value="/updateAppointmentStatus", headers="Accept=application/json")
    @ResponseBody ResponseEntity updateAppointmentStatus(HttpServletRequest request, Appointment appointment) {
        String username = request.getHeader("username");
        // TODO update appointment status
        return null;
    }

}
