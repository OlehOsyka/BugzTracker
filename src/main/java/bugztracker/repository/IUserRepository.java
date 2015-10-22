package bugztracker.repository;

import bugztracker.entity.User;

import java.util.List;

/**
 * Created by Y. Vovk on 17.09.15.
 */
public interface IUserRepository extends IRepository<User> {

    User find(String email);
    List<User> findById(List<Integer> ids);
    List<User> findAll(String query);
    List<User> getUsersByProjectId(int id, String query);
}

