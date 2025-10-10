package emailclient.repository;

import java.util.List;

public interface Repository<T> {
    void add(T entity);
    void update(T entity);
    void delete(T entity);
    T getById(int id);
    List<T> getAll();
}
