package com.bugztracker.persistence.dao.impl;

import com.bugztracker.commons.entity.issue.Issue;
import com.bugztracker.persistence.dao.BaseDao;
import com.bugztracker.persistence.dao.IIssueRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by Oleh_Osyka
 * Date: 14.02.2016
 * Time: 17:43
 */
@Repository
public class IssueRepository extends BaseDao<Issue> implements IIssueRepository {

    public static final String COLLECTION_NAME = "Issues";

    protected IssueRepository() {
        super(COLLECTION_NAME, Issue.class);
    }

    @Override
    protected void createIndex() {
        createIndex(new Index("name", Sort.Direction.ASC).on("creationDate", Sort.Direction.ASC).unique().sparse());
        createIndex(new Index("lastUpdateDate", Sort.Direction.ASC).unique().sparse());
        createIndex(new Index("projectName", Sort.Direction.ASC).on("assigneeEmail", Sort.Direction.ASC).unique().sparse());
    }

    @Override
    public List<Issue> getByProject(String projectName) {
        return findAll(query(where("projectName").is(projectName)));
    }

    @Override
    public List<Issue> getByProjectAndAssignedUser(String projectName, String assignedUserEmail) {
        return findAll(query(where("projectName").is(projectName).and("assigneeEmail").is(assignedUserEmail)));
    }
}
