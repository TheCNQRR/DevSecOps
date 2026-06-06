package by.java.enterprise.employeeservice.mapper;

import by.java.enterprise.employeeservice.dto.*;
import by.java.enterprise.employeeservice.dto.response.EmployeeResponse;
import by.java.enterprise.employeeservice.entity.employee.*;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Name toName(NameDto dto) {
        if (dto == null) return null;
        return Name.builder()
                .title(dto.title())
                .first(dto.first())
                .last(dto.last())
                .build();
    }

    public Street toStreet(StreetDto dto) {
        if (dto == null) return null;
        return Street.builder()
                .number(dto.number())
                .name(dto.name())
                .build();
    }

    public Coordinates toCoordinates(CoordinatesDto dto) {
        if (dto == null) return null;
        return Coordinates.builder()
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .build();
    }

    public Timezone toTimezone(TimezoneDto dto) {
        if (dto == null) return null;
        return Timezone.builder()
                .offset(dto.offset())
                .description(dto.description())
                .build();
    }

    public Location toLocation(LocationDto dto) {
        if (dto == null) return null;
        return Location.builder()
                .street(toStreet(dto.street()))
                .city(dto.city())
                .state(dto.state())
                .country(dto.country())
                .postcode(dto.postcode())
                .coordinates(toCoordinates(dto.coordinates()))
                .timezone(toTimezone(dto.timezone()))
                .build();
    }

    public Login toLogin(LoginDto dto) {
        if (dto == null) return null;
        return Login.builder()
                .uuid(dto.uuid())
                .username(dto.username())
                .password(dto.password())
                .salt(dto.salt())
                .md5(dto.md5())
                .sha1(dto.sha1())
                .sha256(dto.sha256())
                .build();
    }

    public Dob toDob(DobDto dto) {
        if (dto == null) return null;
        return Dob.builder()
                .date(dto.date())
                .age(dto.age())
                .build();
    }

    public Registered toRegistered(RegisteredDto dto) {
        if (dto == null) return null;
        return Registered.builder()
                .date(dto.date())
                .age(dto.age())
                .build();
    }

    public IdInfo toIdInfo(IdInfoDto dto) {
        if (dto == null) return null;
        return IdInfo.builder()
                .name(dto.name())
                .value(dto.value())
                .build();
    }

    public Picture toPicture(PictureDto dto) {
        if (dto == null) return null;
        return Picture.builder()
                .large(dto.large())
                .medium(dto.medium())
                .thumbnail(dto.thumbnail())
                .build();
    }

    public NameDto toNameDto(Name name) {
        if (name == null) return null;
        return new NameDto(name.getTitle(), name.getFirst(), name.getLast());
    }

    public StreetDto toStreetDto(Street street) {
        if (street == null) return null;
        return new StreetDto(street.getNumber(), street.getName());
    }

    public CoordinatesDto toCoordinatesDto(Coordinates coords) {
        if (coords == null) return null;
        return new CoordinatesDto(coords.getLatitude(), coords.getLongitude());
    }

    public TimezoneDto toTimezoneDto(Timezone tz) {
        if (tz == null) return null;
        return new TimezoneDto(tz.getOffset(), tz.getDescription());
    }

    public LocationDto toLocationDto(Location loc) {
        if (loc == null) return null;
        return new LocationDto(
                toStreetDto(loc.getStreet()),
                loc.getCity(),
                loc.getState(),
                loc.getCountry(),
                loc.getPostcode(),
                toCoordinatesDto(loc.getCoordinates()),
                toTimezoneDto(loc.getTimezone())
        );
    }

    public LoginDto toLoginDto(Login login) {
        if (login == null) return null;
        return new LoginDto(
                login.getUuid(), login.getUsername(), login.getPassword(),
                login.getSalt(), login.getMd5(), login.getSha1(), login.getSha256()
        );
    }

    public DobDto toDobDto(Dob dob) {
        if (dob == null) return null;
        return new DobDto(dob.getDate(), dob.getAge());
    }

    public RegisteredDto toRegisteredDto(Registered registered) {
        if (registered == null) return null;
        return new RegisteredDto(registered.getDate(), registered.getAge());
    }

    public IdInfoDto toIdInfoDto(IdInfo idInfo) {
        if (idInfo == null) return null;
        return new IdInfoDto(idInfo.getName(), idInfo.getValue());
    }

    public PictureDto toPictureDto(Picture picture) {
        if (picture == null) return null;
        return new PictureDto(picture.getLarge(), picture.getMedium(), picture.getThumbnail());
    }

    public EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getGender().toString(),
                toNameDto(employee.getName()),
                toLocationDto(employee.getLocation()),
                employee.getEmail(),
                toLoginDto(employee.getLogin()),
                toDobDto(employee.getDob()),
                toRegisteredDto(employee.getRegistered()),
                employee.getPhone(),
                employee.getCell(),
                toIdInfoDto(employee.getIdInfo()),
                toPictureDto(employee.getPicture()),
                employee.getNat()
        );
    }
}
