package by.java.enterprise.employeeservice.service;

import by.java.enterprise.employeeservice.dto.request.CreateEmployeeRequest;
import by.java.enterprise.employeeservice.dto.response.EmployeeResponse;
import by.java.enterprise.employeeservice.entity.OutboxEvent;
import by.java.enterprise.employeeservice.entity.employee.*;
import by.java.enterprise.employeeservice.exception.DuplicateEmployeeException;
import by.java.enterprise.employeeservice.exception.EmployeeNotFoundException;
import by.java.enterprise.employeeservice.mapper.EmployeeMapper;
import by.java.enterprise.employeeservice.repository.EmployeeRepository;
import by.java.enterprise.employeeservice.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OutboxEventRepository outboxEventRepository;
    private final EmployeeMapper mapper;
    private final ObjectMapper objectMapper;
    private final DataSource dataSource;

    @Transactional
    public EmployeeResponse create(CreateEmployeeRequest request) {
        boolean alreadyExists = employeeRepository
                .existsByNameFirstAndNameLast(request.name().first(), request.name().last());

        if (alreadyExists) {
            log.error("employee with first name = {}, last name = {} already exists",
                    request.name().first(), request.name().last());
            throw new DuplicateEmployeeException("employee already exists");
        }

        Employee employee = Employee.builder()
                .gender(Gender.fromString(request.gender()))
                .email(request.email())
                .phone(request.phone())
                .cell(request.cell())
                .nat(request.nat())
                .name(mapper.toName(request.name()))
                .location(mapper.toLocation(request.location()))
                .login(mapper.toLogin(request.login()))
                .dob(mapper.toDob(request.dob()))
                .registered(mapper.toRegistered(request.registered()))
                .idInfo(mapper.toIdInfo(request.idInfo()))
                .picture(mapper.toPicture(request.picture()))
                .build();

        employeeRepository.save(employee);
        log.info("employee saved successfully");
        EmployeeResponse response = mapper.toResponse(employee);

        OutboxEvent outboxEvent = OutboxEvent.builder()
                .aggregateId(employee.getId())
                .payload(objectMapper.writeValueAsString(response))
                .published(false)
                .build();
        outboxEventRepository.save(outboxEvent);
        log.info("outbox event with id {} saved successfully", outboxEvent.getId());

        return response;
    }

    public EmployeeResponse findById(UUID employeeId) {
        Optional<Employee> employeeResult = employeeRepository.findById(employeeId);

        if (employeeResult.isEmpty()) {
            log.warn("employee with id {} doesn't exists", employeeId);
            throw new EmployeeNotFoundException("employee doesn't exists");
        }

        Employee employee = employeeResult.get();

        return mapper.toResponse(employee);
    }
}
