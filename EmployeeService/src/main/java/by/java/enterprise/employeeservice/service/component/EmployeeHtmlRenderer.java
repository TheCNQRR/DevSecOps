package by.java.enterprise.employeeservice.service.component;

import by.java.enterprise.employeeservice.dto.response.EmployeeResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class EmployeeHtmlRenderer {

    private final String htmlTemplate;

    public EmployeeHtmlRenderer() {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream("templates/employee-template.html")) {
            Objects.requireNonNull(inputStream, "template file not found in resources");
            this.htmlTemplate = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("failed to load employee HTML template", e);
        }
    }

    public String render(EmployeeResponse employee) {
        return htmlTemplate.formatted(
                employee.picture() != null ? employee.picture().large() : "",

                employee.name() != null ? employee.name().title() : "",
                employee.name() != null ? employee.name().first() : "",
                employee.name() != null ? employee.name().last() : "",

                employee.nat() != null ? employee.nat() : "",
                employee.gender() != null ? employee.gender() : "",

                employee.id(),
                employee.idInfo() != null ? employee.idInfo().name() : "",
                employee.idInfo() != null ? employee.idInfo().value() : "",
                employee.dob() != null ? (employee.dob().date() != null ? employee.dob().date().toString() : "") : "",
                employee.dob() != null ? employee.dob().age() : 0,
                employee.registered() != null ? (employee.registered().date() != null ? employee.registered().date().toString() : "") : "",
                employee.registered() != null ? employee.registered().age() : 0,

                employee.email() != null ? employee.email() : "",
                employee.phone() != null ? employee.phone() : "",
                employee.cell() != null ? employee.cell() : "",

                employee.location() != null ? (employee.location().street() != null ? employee.location().street().number() : 0) : 0,
                employee.location() != null ? (employee.location().street() != null ? employee.location().street().name() : "") : "",
                employee.location() != null ? employee.location().city() : "",
                employee.location() != null ? employee.location().state() : "",
                employee.location() != null ? employee.location().country() : "",
                employee.location() != null ? employee.location().postcode() : "",

                employee.location() != null ? (employee.location().coordinates() != null ? employee.location().coordinates().latitude() : "") : "",
                employee.location() != null ? (employee.location().coordinates() != null ? employee.location().coordinates().longitude() : "") : "",
                employee.location() != null ? (employee.location().timezone() != null ? employee.location().timezone().offset() : "") : "",
                employee.location() != null ? (employee.location().timezone() != null ? employee.location().timezone().description() : "") : "",

                employee.login() != null ? (employee.login().uuid() != null ? employee.login().uuid().toString() : "") : "",
                employee.login() != null ? employee.login().username() : "",
                employee.login() != null ? employee.login().password() : "",
                employee.login() != null ? employee.login().salt() : "",
                employee.login() != null ? employee.login().md5() : "",
                employee.login() != null ? employee.login().sha1() : "",
                employee.login() != null ? employee.login().sha256() : ""
        );
    }
}
