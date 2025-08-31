package api.apicore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse extends BaseModel{
    private String name;
    private int salary;
    private int age;
    private int id;
}
