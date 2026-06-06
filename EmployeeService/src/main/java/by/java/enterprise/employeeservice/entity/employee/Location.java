package by.java.enterprise.employeeservice.entity.employee;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Embedded
    private Street street;

    private String city;
    private String state;
    private String country;
    private String postcode;

    @Embedded
    private Coordinates coordinates;

    @Embedded
    private Timezone timezone;
}
