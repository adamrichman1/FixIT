package FixIT;

import FixIT.Core.AppointmentDBManager;
import FixIT.Customer.CustomerDBManager;
import FixIT.Staff.StaffDBManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * This class contains the main method of the program and manages teardown operations, which are performed upon system
 * shutdown
 */
@SpringBootApplication
@EnableScheduling
@Controller
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * This method is used on startup and is called implicitly by Spring Boot
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Add shutdown hook so tearDown() is called upon system shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(Application::tearDown));

        // Launch FixIT
        logger.info("------------ FixIT Launching ------------");
        initializeResources();
        SpringApplication.run(Application.class, args);
        logger.info("------------ FixIT Launched Successfully ------------");
    }

    private static void initializeResources() {
        AppointmentDBManager.dropAppointmentTable();
        CustomerDBManager.getInstance().dropUserTable();
        StaffDBManager.getInstance().dropUserTable();
        AppointmentDBManager.createAppointmentTable();
        CustomerDBManager.getInstance().createCustomerTable();
        StaffDBManager.getInstance().createStaffTable();
    }

    /**
     * This method is used for tear-down operations and is called implicitly by Spring Boot upon shutdown
     */
    @PreDestroy
    public static void tearDown() {
        // TODO
    }

    /**
     * Used to convert an object into a String
     *
     * @param o the object to convert to a String
     * @return the Stringified version of hte object in JSON format
     */
    public static String toString(Object o) {
        ObjectWriter ow = new ObjectMapper().writer();
        try {
            return ow.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
