package bugztracker.service;

import java.util.List;

/**
 * Created by Y. Vovk
 * Date: 17.09.15
 * Time: 17:41
 */
public interface IService<T> {

    T get(int id);

    List<T> getAll();

    void add(T entity);

    void delete(T entity);

    void update(T entity);
}
