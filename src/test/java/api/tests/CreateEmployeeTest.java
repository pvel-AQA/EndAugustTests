package api.tests;

import api.apicore.models.EmployeeRequest;
import api.apicore.models.EmployeeResponseStatus;
import api.apicore.requests.Requester;
import api.apicore.requests.ValidatedRequester;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CreateEmployeeTest {
    private Requester requester = new Requester();
    private ValidatedRequester validatedRequester = new ValidatedRequester();

    private static Stream<Arguments> invalidNames() {
        return Stream.of(
                Arguments.of("123", ""),
                Arguments.of("@#$", ""),
                Arguments.of("фвва", ""),
                Arguments.of("dd", ""),
                Arguments.of("d".repeat(101), "")
        );
    }

    @BeforeAll
    public static void beforeAll() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @ParameterizedTest(name = "Employee should not be created with name \"{0}\" -> expects error: \"{1}\"")
    @MethodSource("invalidNames")
    public void employeeShouldBeNotCreatedWithInvalidName(String name, String error) {
        EmployeeRequest employeeRequest = EmployeeRequest.builder()
                .name(name)
                .age(23)
                .salary(3000)
                .build();

        requester.create(employeeRequest)
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(Matchers.contains(error));
    }

    private static Stream<Arguments> validNames() {
        return Stream.of(
                Arguments.of("Daniella"),
                Arguments.of("Cun"),
                Arguments.of("d".repeat(101))
        );
    }

    @ParameterizedTest(name = "Employee should not be created with name \"{0}\" -> expects error: \"{1}\"")
    @MethodSource("validNames")
    public void employeeShouldBeCreatedWithValidName(String name) {
        EmployeeRequest employeeRequest = EmployeeRequest.builder()
                .name(name)
                .age(23)
                .salary(3000)
                .build();

        EmployeeResponseStatus responseStatus = validatedRequester.create(employeeRequest);

        assertAll("Validation of employee",
                () -> assertEquals("success", responseStatus.getStatus()),
                () -> assertEquals(employeeRequest.getName(), responseStatus.getData().getName()),
                () -> assertEquals(employeeRequest.getSalary(), responseStatus.getData().getSalary()),
                () -> assertEquals(employeeRequest.getAge(), responseStatus.getData().getAge()),
                () -> assertNotNull(responseStatus.getData()));
    }
}
