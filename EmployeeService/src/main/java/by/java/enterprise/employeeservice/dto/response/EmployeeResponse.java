package by.java.enterprise.employeeservice.dto.response;

import by.java.enterprise.employeeservice.dto.*;

import java.util.UUID;

public record EmployeeResponse(
        UUID id,

        String gender,

        NameDto name,

        LocationDto location,

        String email,

        LoginDto login,

        DobDto dob,

        RegisteredDto registered,

        String phone,

        String cell,

        IdInfoDto idInfo,

        PictureDto picture,

        String nat
) {
}
