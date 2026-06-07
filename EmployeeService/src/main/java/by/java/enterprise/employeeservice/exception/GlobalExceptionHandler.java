package by.java.enterprise.employeeservice.exception;

import by.java.enterprise.employeeservice.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmployeeException.class)
    public ResponseEntity<?> handleDuplicateEmployeeException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("employee with this first name and last name already exists");
    }

    @ExceptionHandler(FetchRandomUserException.class)
    public ResponseEntity<?> handleFetchEmployeeException() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("external api unavailable");
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleNonExistentEmployeeException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employee doesn't exists");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNotFound(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ресурс не найден: " + ex.getResourcePath());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Внутренняя ошибка сервера: " + e.getMessage()));
    }
}
