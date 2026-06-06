package by.java.enterprise.employeeservice.dto;

import java.util.UUID;

public record LoginDto(
        UUID uuid,

        String username,

        String password,

        String salt,

        String md5,

        String sha1,

        String sha256
) {
}