package by.java.enterprise.employeeservice.dto;

import java.time.OffsetDateTime;

public record DobDto(
        OffsetDateTime date,

        Integer age
) {
}
