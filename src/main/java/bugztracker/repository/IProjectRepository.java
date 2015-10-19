package bugztracker.repository;

import bugztracker.entity.Project;
import bugztracker.entity.User;

import java.util.List;

/**
 * Created by Y. Vovk on 06.10.15.
 */
public interface IProjectRepository extends IRepository<Project> {

    List<Project> search(String text);
    List<Project> getSortedList(String nameField, String option);

    Project getProjectWithUsers(int id);

    List<Project> getProjectsOfUser(User user);
}
