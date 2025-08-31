package ui.tests;

import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.models.Customer;
import ui.pages.RegistrationPage;

public class RegistrationTest {
    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "parabank.parasoft.com";
    }

    @Test
    public void userWithCorrectDataCanRegisterInBank() {
        Customer customer = Customer.generate();

        new RegistrationPage().open()
                .fillCustomerData(customer)
                .register()
                .checkRegistrationIsSuccessful(customer.getUser().getUsername());
    }

    @Test
    public void userWithoutRequiredDataCanNotRegisterInBank() {
        new RegistrationPage().open()
                .register()
                .checkErrorIsVisible("First name is required.")
                .checkErrorIsVisible("Last name is required.")
                .checkErrorIsVisible("Address is required.")
                .checkErrorIsVisible("City is required.")
                .checkErrorIsVisible("State is required.")
                .checkErrorIsVisible("Zip Code is required.")
                .checkErrorIsVisible("Social Security Number is required.")
                .checkErrorIsVisible("Username is required.")
                .checkErrorIsVisible("Password is required.")
                .checkErrorIsVisible("Password confirmation is required.");
    }

    @Test
    public void userWithExistingUsernameCanNotRegisterInBank() {
        // создать кастомер через API если есть возможность
        Customer customer = Customer.generate();

        new RegistrationPage().open()
                .fillCustomerData(customer).register();

        new RegistrationPage().open()
                .fillCustomerData(customer).register()
                .checkErrorIsVisible("This username already exists.");
    }

    @Test
    public void userWithNotMatchingRepeatedPasswordCanNotRegisterInBank() {
        Customer customer = Customer.generate();
        customer.getUser().setPassword(RandomStringUtils.randomAlphanumeric(4));

        new RegistrationPage().open()
                .fillCustomerData(customer).register()
                .checkErrorIsVisible("Password did not match.");
    }
}
