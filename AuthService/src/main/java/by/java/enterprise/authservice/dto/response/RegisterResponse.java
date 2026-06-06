package by.java.enterprise.authservice.dto.response;

import by.java.enterprise.authservice.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record RegisterResponse(
        @JsonProperty("id")
        UUID id,

        @JsonProperty("username")
        String username,

        @JsonProperty("role")
        UserRole role
) {
}
