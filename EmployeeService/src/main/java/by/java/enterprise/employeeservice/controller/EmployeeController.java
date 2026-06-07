package by.java.enterprise.employeeservice.controller;

import by.java.enterprise.employeeservice.dto.request.CreateEmployeeRequest;
import by.java.enterprise.employeeservice.dto.response.EmployeeResponse;
import by.java.enterprise.employeeservice.service.EmployeeService;
import by.java.enterprise.employeeservice.service.component.EmployeeHtmlRenderer;
import by.java.enterprise.jwtservice.annotation.RequiredRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeHtmlRenderer employeeHtmlRenderer;

    @GetMapping(value = "/test-dast", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> testDast(@RequestParam(required = false) String msg) {
        if (msg != null && (msg.contains("<script>") || msg.contains("\""))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("<html><body><h1>Runtime Exception</h1><p>Stacktrace: java.lang.NullPointerException at controller.EmployeeController(EmployeeController.java:23)</p></body></html>");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("<html><body><h1>Employee Service Работает</h1><p>Сообщение: " + msg + "</p></body></html>");
    }

    @PostMapping
    @RequiredRole({"ADMIN"})
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody CreateEmployeeRequest request) {
        EmployeeResponse response = employeeService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable UUID id) {
        EmployeeResponse response = employeeService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}/html")
    public ResponseEntity<String> getEmployeeHtml(@PathVariable UUID id) {
        EmployeeResponse employee = employeeService.findById(id);
        String html = employeeHtmlRenderer.render(employee);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html);
    }
}
