package bugztracker.repository.impl;

import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.repository.AbstractRepository;
import bugztracker.repository.IProjectRepository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Y. Vovk on 06.10.15.
 */
@Repository
public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public ProjectRepository() {
        super(Project.class);
    }

    @Override
    public List<Project> search(String text) {
        return (List<Project>) sessionFactory.getCurrentSession().createCriteria(Project.class)
                .add(Restrictions.or(
                        Restrictions.ilike("name", text, MatchMode.ANYWHERE),
                        Restrictions.ilike("description", text, MatchMode.ANYWHERE)
                )).list();
    }

    @Override
    public List<Project> getSortedList(String nameField, String option) {
        Order order = option.equalsIgnoreCase("asc") ? Order.asc(nameField) : Order.desc(nameField);
        return (List<Project>) sessionFactory.getCurrentSession()
                .createCriteria(Project.class)
                .addOrder(order).list();
    }

    @Override
    public List<Project> getAllWithParticipants() {
        return (List<Project>) sessionFactory.getCurrentSession()
                .createCriteria(Project.class)
                .setFetchMode("participants", FetchMode.JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                //.addOrder(Order.asc("name"))
                .list();
    }

    @Override
    public Project getProjectWithUsers(int id) {
        return (Project) sessionFactory.getCurrentSession().createCriteria(Project.class)
                .add(Restrictions.eq("id", id))
                .setFetchMode("participants", FetchMode.JOIN)
                .uniqueResult();
    }

    @Override
    public List<Project> getProjectsOfUser(User user) {
        DetachedCriteria subCriteria = DetachedCriteria.forClass(Project.class);
        subCriteria.createAlias("participants", "parts", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("parts.id", user.getId()))
                .setProjection(Projections.property("id"));

        return (List<Project>) sessionFactory.getCurrentSession()
                .createCriteria(Project.class)
                .setFetchMode("participants", FetchMode.JOIN)
                .add(Subqueries.propertyIn("id", subCriteria))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder(Order.asc("name"))
                .list();
    }
}
