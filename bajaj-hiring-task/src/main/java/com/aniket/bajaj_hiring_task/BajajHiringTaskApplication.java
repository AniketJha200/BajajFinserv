package com.katoch.bajaj_hiring_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BajajHiringTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(BajajHiringTaskApplication.class, args);
    }

    /**
     * Creates a RestTemplate bean to be managed by the Spring container.
     * This allows RestTemplate to be @Autowired in other components,
     * such as the HiringTaskService, for making HTTP requests.
     *
     * @return A new instance of RestTemplate.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}