package by.java.enterprise.employeeservice.dto;

public record LocationDto(
        StreetDto street,

        String city,

        String state,

        String country,

        String postcode,

        CoordinatesDto coordinates,

        TimezoneDto timezone
) {
}