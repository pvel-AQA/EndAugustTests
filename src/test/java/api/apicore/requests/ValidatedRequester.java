package api.apicore.requests;

import api.apicore.models.EmployeeRequest;
import api.apicore.models.EmployeeResponseStatus;

import java.util.List;

public class ValidatedRequester implements CrudInterface<EmployeeRequest>{
    private Requester requester = new Requester();

    @Override
    public EmployeeResponseStatus create(EmployeeRequest model) {
        return requester.create(model).extract().as(EmployeeResponseStatus.class);
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
