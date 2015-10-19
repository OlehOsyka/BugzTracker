package bugztracker.repository.impl;

import bugztracker.entity.User;
import bugztracker.repository.AbstractRepository;
import bugztracker.repository.IUserRepository;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                .add(Restrictions.eq("email", email))
                .setFetchMode("projects", FetchMode.JOIN)
                .uniqueResult();
    }

    @Override
    public List<User> findById(List<Integer> ids){
        return sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.in("id",ids))
                .setFetchMode("projects", FetchMode.JOIN)
                .list();
    }

    @Override
    public List<User> findAll(String query) {
        return sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.ilike("fullName", query, MatchMode.START))
                .setFetchMode("projects", FetchMode.JOIN)
                .list();
    }
}
