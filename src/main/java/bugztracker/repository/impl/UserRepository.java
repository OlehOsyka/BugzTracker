package bugztracker.repository.impl;

import bugztracker.entity.User;
import bugztracker.repository.AbstractRepository;
import bugztracker.repository.IUserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Y. Vovk on 17.09.15.
 */
@Repository
public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Autowired
    protected SessionFactory sessionFactory;

    public UserRepository() {
        super(User.class);
    }

    @Override
    public User find(String email) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
    }
}
