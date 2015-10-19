package bugztracker.service;

import bugztracker.bean.LoginBean;
import bugztracker.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Y. Vovk on 17.09.15.
 */
public interface IUserService extends IService<User> {

    User find(String email);
    List<User> findAll(String query);
    List<User> findByIds(List<Integer> ids);
    Map<String, String> login(User user, LoginBean loginBean);
    Map<String, String> register(User user);
    List<Integer> getProjectsIdsOfUser(User user);
}
