package bugztracker.service;

import bugztracker.entity.User;

import java.util.List;

/**
 * Created by Y. Vovk on 17.09.15.
 */
public interface IUserService extends IService<User> {

    User find(String email);

    List<User> findAll(String query);

    List<User> findByIds(List<Integer> ids);
}
