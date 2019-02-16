package FixIT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import javax.annotation.PreDestroy;

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
        SpringApplication.run(Application.class, args);
        logger.info("------------ FixIT Launched Successfully ------------");
    }

    /**
     * This method is used for tear-down operations and is called implicitly by Spring Boot upon shutdown
     */
    @PreDestroy
    public static void tearDown() {
        // TODO
    }
}
