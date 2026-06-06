package by.java.enterprise.employeeservice.service;

import by.java.enterprise.employeeservice.dto.request.CreateEmployeeRequest;
import by.java.enterprise.employeeservice.dto.response.RandomUserResponse;
import by.java.enterprise.employeeservice.dto.response.RandomUserResult;
import by.java.enterprise.employeeservice.exception.FetchRandomUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class RandomUserFetcher {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${randomuser.api.url}")
    private String apiUrl;

    public CreateEmployeeRequest fetchRandomEmployee() {
        ResponseEntity<String> rawResponse = restTemplate.getForEntity(apiUrl, String.class);
        String json = rawResponse.getBody();
        log.info("Raw JSON: {}", json);

        RandomUserResponse response;
        try {
            response = objectMapper.readValue(json, RandomUserResponse.class);
        } catch (Exception e) {
            log.error("failed to parse JSON from randomuser.me", e);
            throw new FetchRandomUserException("invalid response from randomuser.me");
        }

        if (response == null || response.results() == null || response.results().isEmpty()) {
            log.error("invalid response from randomuser.me");
            throw new FetchRandomUserException("invalid response from randomuser.me");
        }

        RandomUserResult result = response.results().getFirst();
        log.info("received successful response from randomuser.me");

        return CreateEmployeeRequest.builder()
                .gender(result.gender())
                .name(result.name())
                .location(result.location())
                .email(result.email())
                .login(result.login())
                .dob(result.dob())
                .registered(result.registered())
                .phone(result.phone())
                .cell(result.cell())
                .idInfo(result.idInfo())
                .picture(result.picture())
                .nat(result.nat())
                .build();
    }
}
