package by.java.enterprise.authservice.service;

import by.java.enterprise.authservice.dto.request.LoginRequest;
import by.java.enterprise.authservice.dto.request.RegisterRequest;
import by.java.enterprise.authservice.dto.response.LoginResponse;
import by.java.enterprise.authservice.dto.response.RegisterResponse;
import by.java.enterprise.authservice.entity.User;
import by.java.enterprise.authservice.entity.UserRole;
import by.java.enterprise.authservice.exception.LoginException;
import by.java.enterprise.authservice.exception.RegistrationException;
import by.java.enterprise.authservice.repository.AuthRepository;
import by.java.enterprise.jwtservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest request) {
        Optional<User> userResult = authRepository.findByUsername(request.username());

        if (userResult.isPresent()) {
            throw new RegistrationException("user with that username already exists");
        }

        User newUser = User.builder()
                .username(request.username())
                .passwordHash(encoder.encode(request.password()))
                .role(UserRole.valueOf(request.role()))
                .build();

        authRepository.save(newUser);
        log.info("registered user with id={}, username={}, role={}",
                newUser.getId(),
                newUser.getUsername(),
                newUser.getRole());

        return new RegisterResponse(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getRole()
        );
    }

    public LoginResponse login(LoginRequest request) {
        Optional<User> userResult = authRepository.findByUsername(request.username());

        if (userResult.isEmpty()) {
            throw new LoginException("user with that username doesn't exists");
        }

        User user = userResult.get();

        if (!encoder.matches(request.password(), user.getPasswordHash())) {
            throw new LoginException("invalid credentials");
        }

        String token = jwtService.generateToken(user.getId(), user.getRole().toString());
        log.info("user with id={} logged in", user.getId());

        return new LoginResponse(token);
    }
}
