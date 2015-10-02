package bugztracker.repository;

import bugztracker.entity.User;

/**
 * Created by Y. Vovk on 17.09.15.
 */
public interface IUserRepository extends IRepository<User> {

    User find(String email);

}

