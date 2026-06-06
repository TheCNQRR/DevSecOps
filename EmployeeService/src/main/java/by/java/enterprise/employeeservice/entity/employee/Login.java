package by.java.enterprise.employeeservice.entity.employee;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    private UUID uuid;
    private String password;
    private String username;
    private String salt;
    private String md5;
    private String sha1;
    private String sha256;
}
