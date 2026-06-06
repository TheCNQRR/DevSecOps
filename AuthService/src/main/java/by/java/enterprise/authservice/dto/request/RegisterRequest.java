package by.java.enterprise.authservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @JsonProperty("username")
        @NotBlank(message = "username must be not null")
        @Size(max = 255, message = "username length must not exceed 255 characters")
        String username,

        @JsonProperty("password")
        @NotBlank(message = "password name must be not null")
        @Size(min = 2, max = 32, message = "password length must be between 2 and 32 characters long")
        String password,

        @JsonProperty("role")
        @Pattern(regexp = "^(ADMIN|USER)$", message = "role must be either 'ADMIN' or 'USER'")
        String role
) {
}
