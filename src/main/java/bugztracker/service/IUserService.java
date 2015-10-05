package bugztracker.service;

import bugztracker.entity.User;

/**
 * Created by Y. Vovk on 17.09.15.
 */
public interface IUserService extends IService<User> {

    User find(String email);
}
