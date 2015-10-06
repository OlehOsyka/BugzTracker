package bugztracker.repository.impl;

import bugztracker.entity.Project;
import bugztracker.repository.AbstractRepository;
import bugztracker.repository.IProjectRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Y. Vovk on 06.10.15.
 */
@Repository
public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    public ProjectRepository() {
        super(Project.class);
    }
}
