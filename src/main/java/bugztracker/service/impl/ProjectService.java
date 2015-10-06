package bugztracker.service.impl;

import bugztracker.entity.Project;
import bugztracker.repository.IProjectRepository;
import bugztracker.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Y. Vovk on 06.10.15.
 */
@Transactional
@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public Project get(long id) {
        return projectRepository.get(id);
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
}
