package by.java.enterprise.employeeservice.dto.request;

import by.java.enterprise.employeeservice.dto.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CreateEmployeeRequest(
        String gender,

        NameDto name,

        LocationDto location,

        String email,

        LoginDto login,

        DobDto dob,

        RegisteredDto registered,

        String phone,

        String cell,

        @JsonProperty("id")
        IdInfoDto idInfo,

        PictureDto picture,

        String nat
) {
}