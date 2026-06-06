package by.java.enterprise.employeeservice.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.CompositeConverter;

import java.util.regex.Pattern;

public class MaskingConverter extends CompositeConverter<ILoggingEvent> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\b\\+?[\\d\\s()-]{7,20}\\b");
    private static final Pattern ID_VALUE_PATTERN = Pattern.compile("\"value\"\\s*:\\s*\"[^\"]*\"");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("\"password\"\\s*:\\s*\"[^\"]*\"");
    private static final Pattern SALT_PATTERN = Pattern.compile("\"salt\"\\s*:\\s*\"[^\"]*\"");
    private static final Pattern MD5_PATTERN = Pattern.compile("\"md5\"\\s*:\\s*\"[^\"]*\"");
    private static final Pattern SHA1_PATTERN = Pattern.compile("\"sha1\"\\s*:\\s*\"[^\"]*\"");
    private static final Pattern SHA256_PATTERN = Pattern.compile("\"sha256\"\\s*:\\s*\"[^\"]*\"");
    private static final Pattern LOCATION_STREET_PATTERN = Pattern.compile("\"street\"\\s*:\\s*\\{\"number\":\\d+,\"name\":\"[^\"]*\"\\}");
    private static final Pattern LOCATION_CITY_PATTERN = Pattern.compile("\"city\"\\s*:\\s*\"[^\"]*\"");
    private static final Pattern LOCATION_STATE_PATTERN = Pattern.compile("\"state\"\\s*:\\s*\"[^\"]*\"");
    private static final Pattern LOCATION_COUNTRY_PATTERN = Pattern.compile("\"country\"\\s*:\\s*\"[^\"]*\"");
    private static final Pattern LOCATION_POSTCODE_PATTERN = Pattern.compile("\"postcode\"\\s*:\\s*\"?[^\",}]+");
    private static final Pattern LOCATION_COORDINATES_PATTERN = Pattern.compile("\"coordinates\"\\s*:\\s*\\{\"latitude\":\"[^\"]*\",\"longitude\":\"[^\"]*\"\\}");
    private static final Pattern LOCATION_TIMEZONE_PATTERN = Pattern.compile("\"timezone\"\\s*:\\s*\\{\"offset\":\"[^\"]*\",\"description\":\"[^\"]*\"\\}");

    @Override
    protected String transform(ILoggingEvent event, String in) {
        String message = in;
        message = EMAIL_PATTERN.matcher(message).replaceAll("***@***.***");
        message = PHONE_PATTERN.matcher(message).replaceAll("******");
        message = ID_VALUE_PATTERN.matcher(message).replaceAll("\"value\":\"***\"");
        message = PASSWORD_PATTERN.matcher(message).replaceAll("\"password\":\"***\"");
        message = SALT_PATTERN.matcher(message).replaceAll("\"salt\":\"***\"");
        message = MD5_PATTERN.matcher(message).replaceAll("\"md5\":\"***\"");
        message = SHA1_PATTERN.matcher(message).replaceAll("\"sha1\":\"***\"");
        message = SHA256_PATTERN.matcher(message).replaceAll("\"sha256\":\"***\"");
        message = LOCATION_STREET_PATTERN.matcher(message).replaceAll("\"street\":{\"number\":0,\"name\":\"***\"}");
        message = LOCATION_CITY_PATTERN.matcher(message).replaceAll("\"city\":\"***\"");
        message = LOCATION_STATE_PATTERN.matcher(message).replaceAll("\"state\":\"***\"");
        message = LOCATION_COUNTRY_PATTERN.matcher(message).replaceAll("\"country\":\"***\"");
        message = LOCATION_POSTCODE_PATTERN.matcher(message).replaceAll("\"postcode\":\"***\"");
        message = LOCATION_COORDINATES_PATTERN.matcher(message).replaceAll("\"coordinates\":{\"latitude\":\"***\",\"longitude\":\"***\"}");
        message = LOCATION_TIMEZONE_PATTERN.matcher(message).replaceAll("\"timezone\":{\"offset\":\"***\",\"description\":\"***\"}");
        return message;
    }
}
