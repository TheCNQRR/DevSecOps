package by.java.enterprise.authservice.controller;

import by.java.enterprise.authservice.dto.request.LoginRequest;
import by.java.enterprise.authservice.dto.request.RegisterRequest;
import by.java.enterprise.authservice.dto.response.LoginResponse;
import by.java.enterprise.authservice.dto.response.RegisterResponse;
import by.java.enterprise.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

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
