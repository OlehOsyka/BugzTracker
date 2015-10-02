package bugztracker.service;

import java.util.List;

/**
 * Created by Y. Vovk on 17.09.15.
 */
public interface IService<T> {

    T get(long id);

    List<T> getAll();

    void add(T entity);

    void delete(T entity);

    void update(T entity);
}
