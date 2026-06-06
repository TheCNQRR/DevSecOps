package by.java.enterprise.employeeservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RandomUserResponse(
        @JsonProperty("results")
        List<RandomUserResult> results
) {
}
