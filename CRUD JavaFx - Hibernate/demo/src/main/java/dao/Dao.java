package dao;

import java.util.List;

public interface Dao<T> {
    public void create(T t) throws Exception;
    public T read(int id) throws Exception;
    public List<T> readAll();
    public void update(T t) throws Exception;
    public void delete(int id) throws Exception;

}
