package com.bugztracker.persistence.dao.impl;

import com.bugztracker.commons.entity.project.Project;
import com.bugztracker.persistence.dao.BaseDao;
import com.bugztracker.persistence.dao.IProjectRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by Oleh_Osyka for EPMC-CLO
 * Date: 14.02.2016
 * Time: 17:11
 */
@Repository
public class ProjectRepository extends BaseDao<Project> implements IProjectRepository {

    public static final String COLLECTION_NAME = "Projects";

    protected ProjectRepository() {
        super(COLLECTION_NAME, Project.class);
    }

    @Override
    protected void createIndex() {
        createIndex(new Index("name", Sort.Direction.ASC).on("date", Sort.Direction.ASC).unique().sparse());
    }

    @Override
    public Project findByProjectName(String name) {
        return findOne(query(where("name").is(name)));
    }
}
