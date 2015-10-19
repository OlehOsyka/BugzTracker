package bugztracker.service.impl;

import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.repository.IProjectRepository;
import bugztracker.repository.IUserRepository;
import bugztracker.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Created by Y. Vovk on 06.10.15.
 */
@Transactional
@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Project get(int id) {
        return projectRepository.get(id);
    }

    @Override
    public Project getWithUsers(int id) {
        return projectRepository.getProjectWithUsers(id);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    @Override
    public void add(Project entity) {
        projectRepository.add(entity);
    }

    @Override
    public void delete(Project entity) {
        projectRepository.delete(entity);
    }

    @Override
    public void update(Project entity) {
        projectRepository.update(entity);
    }

    @Override
    public List<Project> search(String text) {
        return projectRepository.search(text);
    }

    @Override
    public List<Project> getSortedList(String nameField, String option) {
        return projectRepository.getSortedList(nameField, option);
    }

    @Override
    public List<Project> getProjectsOfUser(User user) {
        return projectRepository.getProjectsOfUser(user);
    }

    @Override
    public List<Project> getAllWithParticipants() {
        return projectRepository.getAllWithParticipants();
    }

    @Override
    public void updateProject(Project project) {
        Project proj = get(project.getId());

        List<Integer> ids = new ArrayList<>();
        for (User current : project.getParticipants()) {
            ids.add(current.getId());
        }

        proj.setDescription(project.getDescription());
        proj.setName(project.getName());
        proj.setParticipants(new HashSet<>(userRepository.findById(ids)));

        update(proj);
    }

    @Override
    public void addProject(Project project) {
        project.setId(UUID.randomUUID().clockSequence());
        project.setDate(new Date(System.currentTimeMillis()));

        add(project);
    }
}
