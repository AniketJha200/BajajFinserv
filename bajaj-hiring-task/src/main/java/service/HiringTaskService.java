package service;

import com.katoch.bajaj_hiring_task.dto.SolutionRequest;
import com.katoch.bajaj_hiring_task.dto.WebhookRequest;
import com.katoch.bajaj_hiring_task.dto.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HiringTaskService {

    private static final Logger logger = LoggerFactory.getLogger(HiringTaskService.class);

    @Value("${api.url.generateWebhook}")
    private String generateWebhookUrl;

    @Value("${user.name}")
    private String userName;

    @Value("${user.regNo}")
    private String userRegNo;

    @Value("${user.email}")
    private String userEmail;

    @Autowired
    private RestTemplate restTemplate;

    public void executeHiringTask() {
        try {
            WebhookResponse response = generateWebhook();
            if (response != null && response.getAccessToken() != null) {
                logger.info("Successfully received webhook and token.");
                submitSolution(response);
            } else {
                logger.error("Failed to generate webhook or receive access token.");
            }
        } catch (Exception e) {
            logger.error("An error occurred during the hiring task execution: {}", e.getMessage());
        }
    }

    private WebhookResponse generateWebhook() {
        WebhookRequest requestBody = new WebhookRequest(userName, userRegNo, userEmail);
        logger.info("Sending request to generate webhook: {}", requestBody);
        WebhookResponse response = restTemplate.postForObject(generateWebhookUrl, requestBody, WebhookResponse.class);
        logger.info("Received response: {}", response);
        return response;
    }

    private void submitSolution(WebhookResponse webhookResponse) {
        // The SQL query is now retrieved directly.
        String finalQuery = getFinalSqlQuery();
        SolutionRequest solutionBody = new SolutionRequest(finalQuery);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(webhookResponse.getAccessToken());

        HttpEntity<SolutionRequest> requestEntity = new HttpEntity<>(solutionBody, headers);
        String submissionUrl = webhookResponse.getWebhookURL();

        logger.info("Submitting solution to: {}", submissionUrl);
        logger.info("Request body: {}", solutionBody);
        String result = restTemplate.postForObject(submissionUrl, requestEntity, String.class);
        logger.info("Submission result: {}", result);
    }

    /**
     * Returns the specific SQL query required for the task.
     */
    private String getFinalSqlQuery() {
        logger.info("Using the predefined SQL query for ODD NUMBER");
        return """
               SELECT  
               P.AMOUNT AS SALARY,
               CONCAT(E.FIRST_NAME, ' ', E.LAST_NAME) AS NAME,
               TIMESTAMPDIFF(YEAR, E.DOB, CURDATE()) AS AGE,
               D.DEPARTMENT_NAME
               FROM PAYMENTS P
               JOIN EMPLOYEE E ON P.EMP_ID = E.EMP_ID
               JOIN DEPARTMENT D ON E.DEPARTMENT = D.DEPARTMENT_ID
               WHERE DAY(P.PAYMENT_TIME) <> 1
               ORDER BY P.AMOUNT DESC
               LIMIT 1;
               """;
    }
}