package ui.models;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@Builder
public class Customer {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String ssn;
    private User user;

    public static Customer generate() {
        String password = RandomStringUtils.randomAlphanumeric(10);

        return Customer.builder()
                .firstName(RandomStringUtils.randomAlphabetic(5))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .address(RandomStringUtils.randomAlphabetic(5))
                .city(RandomStringUtils.randomAlphabetic(5))
                .state(RandomStringUtils.randomAlphabetic(5))
                .zipCode(RandomStringUtils.randomNumeric(5))
                .phone(RandomStringUtils.randomNumeric(10))
                .ssn(RandomStringUtils.randomNumeric(10))
                .user(User.builder()
                        .username(RandomStringUtils.randomAlphabetic(5))
                        .password(password)
                        .repeatedPassword(password)
                        .build())
                .build();
    }
}
