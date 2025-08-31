package api.apicore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseStatus extends BaseModel {
    private String status;
    private String message;
    public EmployeeResponse data;
}
