package api.apicore.requests;

import api.apicore.models.EmployeeRequest;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Requester implements CrudInterface<EmployeeRequest>{
    private static final String BASE_URL = "https://dummy.restapiexample.com";
    private static final String API_VERSION = "/api/v1";

    @Override
    public ValidatableResponse create(EmployeeRequest model) {
        return given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(BASE_URL + API_VERSION + "/create")
                .then();
    }

    @Override
    public Object read(int id) {
        return null;
    }

    @Override
    public Object update(EmployeeRequest model) {
        return null;
    }

    @Override
    public Object delete(int id) {
        return null;
    }

    @Override
    public List<Object> readAll() {
        return List.of();
    }
}
