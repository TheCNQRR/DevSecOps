package by.java.enterprise.employeeservice.dto;

import java.time.OffsetDateTime;

public record RegisteredDto(
        OffsetDateTime date,

        Integer age
) {
}
