package api.apicore.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequest extends BaseModel{
    private String name;
    private int salary;
    private int age;
}
