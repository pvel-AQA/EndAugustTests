package api.apicore.requests;

import api.apicore.models.BaseModel;

import java.util.List;

public interface CrudInterface<T extends BaseModel> {
    public Object create(T model);

    public Object read(int id);

    public Object update(T model);

    public Object delete(int id);

    public List<Object> readAll();
}
