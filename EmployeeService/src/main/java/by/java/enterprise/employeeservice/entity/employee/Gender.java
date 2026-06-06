package by.java.enterprise.employeeservice.entity.employee;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    male, female;

    @JsonCreator
    public static Gender fromString(String value) {
        if (value == null) {
            return null;
        }

        return Gender.valueOf(value.toLowerCase());
    }
}
