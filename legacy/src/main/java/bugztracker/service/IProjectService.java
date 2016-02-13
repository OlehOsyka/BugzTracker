package bugztracker.service;

import bugztracker.entity.Project;
import bugztracker.entity.User;

import java.util.List;

/**
 * Created by Y. Vovk on 06.10.15.
 */
public interface IProjectService extends IService<Project> {

    Project getWithUsers(int id);

    List<Project> search(String text);

    List<Project> getSortedList(String nameField, String option);

    List<Project> getProjectsOfUser(User user);

    List<Project> getAllWithParticipants();

    void updateProject(Project project);

    void addProject(Project project, User user);
}
