package by.java.enterprise.authservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @JsonProperty("username")
        @NotBlank(message = "username must be not null")
        @Size(max = 255, message = "username length must not exceed 255 characters")
        String username,

        @JsonProperty("password")
        String password
) {
}
