package by.java.enterprise.employeeservice.scheduler;

import by.java.enterprise.employeeservice.dto.request.CreateEmployeeRequest;
import by.java.enterprise.employeeservice.service.EmployeeService;
import by.java.enterprise.employeeservice.service.RandomUserFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeScheduler {

    private final RandomUserFetcher userFetcher;
    private final EmployeeService employeeService;

    @Scheduled(cron = "${scheduler.cron}")
    public void fetchAndSaveEmployee() {
        try {
            log.info("starting scheduled daily employee fetch");
            CreateEmployeeRequest request = userFetcher.fetchRandomEmployee();
            employeeService.create(request);
            log.info("employee fetch and save completed successfully.");
        } catch (Exception e) {
            log.error("failed to fetch or save random employee: {}", e.getMessage());
        }
    }
}
