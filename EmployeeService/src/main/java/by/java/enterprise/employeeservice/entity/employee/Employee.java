package by.java.enterprise.employeeservice.entity.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "employee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "title", column = @Column(name = "name_title")),
            @AttributeOverride(name = "first", column = @Column(name = "name_first")),
            @AttributeOverride(name = "last", column = @Column(name = "name_last"))
    })
    private Name name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street.number", column = @Column(name = "location_street_number")),
            @AttributeOverride(name = "street.name", column = @Column(name = "location_street_name")),
            @AttributeOverride(name = "city", column = @Column(name = "location_city")),
            @AttributeOverride(name = "state", column = @Column(name = "location_state")),
            @AttributeOverride(name = "country", column = @Column(name = "location_country")),
            @AttributeOverride(name = "postcode", column = @Column(name = "location_postcode")),
            @AttributeOverride(name = "coordinates.latitude", column = @Column(name = "location_coordinates_latitude")),
            @AttributeOverride(name = "coordinates.longitude", column = @Column(name = "location_coordinates_longitude")),
            @AttributeOverride(name = "timezone.offset", column = @Column(name = "location_timezone_offset")),
            @AttributeOverride(name = "timezone.description", column = @Column(name = "location_timezone_description"))
    })
    private Location location;

    @Column(name = "email")
    private String email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "uuid", column = @Column(name = "login_uuid")),
            @AttributeOverride(name = "username", column = @Column(name = "login_username")),
            @AttributeOverride(name = "password", column = @Column(name = "login_password")),
            @AttributeOverride(name = "salt", column = @Column(name = "login_salt")),
            @AttributeOverride(name = "md5", column = @Column(name = "login_md5")),
            @AttributeOverride(name = "sha1", column = @Column(name = "login_sha1")),
            @AttributeOverride(name = "sha256", column = @Column(name = "login_sha256"))
    })
    private Login login;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "date", column = @Column(name = "dob_date")),
            @AttributeOverride(name = "age", column = @Column(name = "dob_age"))
    })
    private Dob dob;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "date", column = @Column(name = "registered_date")),
            @AttributeOverride(name = "age", column = @Column(name = "registered_age"))
    })
    private Registered registered;

    @Column(name = "phone")
    private String phone;

    @Column(name = "cell")
    private String cell;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "id_info_name")),
            @AttributeOverride(name = "value", column = @Column(name = "id_info_value"))
    })
    private IdInfo idInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "large", column = @Column(name = "picture_large")),
            @AttributeOverride(name = "medium", column = @Column(name = "picture_medium")),
            @AttributeOverride(name = "thumbnail", column = @Column(name = "picture_thumbnail"))
    })
    private Picture picture;

    @Column(name = "nat")
    private String nat;
}
