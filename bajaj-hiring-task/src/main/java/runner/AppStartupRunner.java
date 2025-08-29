package runner;

import service.HiringTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This class is a Spring Component that runs automatically on application startup.
 * Its purpose is to trigger the main business logic in the HiringTaskService.
 */
@Component
public class AppStartupRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);

    // The service containing the core application logic.
    private final HiringTaskService hiringTaskService;

    /**
     * Constructor-based dependency injection.
     * Spring will automatically provide an instance of HiringTaskService.
     *
     * @param hiringTaskService The service to be executed.
     */
    @Autowired
    public AppStartupRunner(HiringTaskService hiringTaskService) {
        this.hiringTaskService = hiringTaskService;
    }

    /**
     * This method is executed by Spring Boot upon application startup.
     *
     * @param args Command line arguments (not used in this application).
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        logger.info("Application started, executing the hiring task...");
        // Triggers the main logic in the service class.
        hiringTaskService.executeHiringTask();
        logger.info("Hiring task execution has been initiated.");
    }
}