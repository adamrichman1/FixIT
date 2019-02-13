package FixIT.Core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * This class manages HTTP endpoints for appointment-related functionality (e.g. createAppointment)
 */
@RestController
public class AppointmentRestController extends FixITRestController {

    /**
     * Called when a customer tries to create an appointment
     *
     * @param request the HttpRequest entity containing header information
     * @param appointment the appointment object containing appointment data requested by a customer
     * @return a ResponseEntity to the user
     */
    @RequestMapping(method= RequestMethod.POST, value="/createAppointment", headers="Accept=application/json")
    protected @ResponseBody ResponseEntity createAppointment(HttpServletRequest request, @RequestBody Appointment appointment) {
        String username = request.getHeader("username");

        // TODO create appointment
        return null;
    }
}
