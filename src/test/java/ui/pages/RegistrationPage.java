package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ui.models.Customer;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends BasePage{
    private static String RELATIVE_URL = "/parabank/register.htm";

    private SelenideElement firstnameField = $("#customer\\.firstname");
    private SelenideElement lastnameField = $("#customer\\.lastname");
    private SelenideElement addressField = $("#customer\\.address\\.street");
    private SelenideElement cityField = $("#customer\\.address\\.city");
    private SelenideElement stateField = $("#customer\\.address\\.state");
    private SelenideElement zipCodeField = $("#customer\\.address\\.zipCode");
    private SelenideElement phoneNumberField = $("#customer\\.phoneNumber");
    private SelenideElement ssnField = $("#customer\\.ssn");
    private SelenideElement usernameField = $("#customer\\.username");
    private SelenideElement passwordField = $("#customer\\.password");
    private SelenideElement repeatedPasswordField = $("#repeatedPassword");
    private SelenideElement submitButton = $(Selectors.byAttribute("value", "Register"));


    @Override
    public RegistrationPage open() {
        Selenide.open(RELATIVE_URL);
        return this;
    }

    public RegistrationPage fillCustomerData(Customer customer) {
        firstnameField.sendKeys(customer.getFirstName());
        lastnameField.sendKeys(customer.getLastName());
        addressField.sendKeys(customer.getAddress());
        cityField.sendKeys(customer.getCity());
        stateField.sendKeys(customer.getState());
        zipCodeField.sendKeys(customer.getZipCode());
        phoneNumberField.sendKeys(customer.getPhone());
        ssnField.sendKeys(customer.getSsn());
        usernameField.sendKeys(customer.getUser().getUsername());
        passwordField.sendKeys(customer.getUser().getPassword());
        repeatedPasswordField.sendKeys(customer.getUser().getRepeatedPassword());

        return this;
    }

    public RegistrationPage register() {
        submitButton.click();
        return this;
    }

    public RegistrationPage checkErrorIsVisible(String errorText) {
        $(Selectors.byText(errorText)).shouldBe(Condition.visible);
        return this;
    }

    public RegistrationPage checkRegistrationIsSuccessful(String username) {
        $(Selectors.byText("Welcome " + username)).shouldBe(Condition.visible);
        return this;
    }
}
