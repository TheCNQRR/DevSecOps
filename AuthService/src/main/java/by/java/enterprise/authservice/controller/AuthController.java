package by.java.enterprise.authservice.controller;

import by.java.enterprise.authservice.dto.request.LoginRequest;
import by.java.enterprise.authservice.dto.request.RegisterRequest;
import by.java.enterprise.authservice.dto.response.LoginResponse;
import by.java.enterprise.authservice.dto.response.RegisterResponse;
import by.java.enterprise.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping(value = "/test-dast", produces = MediaType.TEXT_HTML_VALUE)
    public String testDast() {
        return "<html><body><h1>Auth Service Работает</h1></body></html>";
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse user = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse token = authService.login(request);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
