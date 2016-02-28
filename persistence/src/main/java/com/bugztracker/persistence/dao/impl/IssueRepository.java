package com.bugztracker.persistence.dao.impl;

import com.bugztracker.commons.entity.issue.Issue;
import com.bugztracker.persistence.dao.BaseDao;
import com.bugztracker.persistence.dao.IIssueRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
        createIndex(new Index("name", Sort.Direction.ASC).unique());
        createIndex(new Index("status", Sort.Direction.ASC).on("category", Sort.Direction.ASC).on("priority", Sort.Direction.ASC).sparse());
        createIndex(new Index("projectName", Sort.Direction.ASC).on("assigneeEmail", Sort.Direction.ASC).sparse());
    }

    @Override
    public List<Issue> getByProject(String projectName) {
        return findAll(query(where("projectName").is(projectName)));
    }

    @Override
    public List<Issue> getByProjectAndStatus(String projectName, String status) {
        return findAll(query(where("projectName").is(projectName).and("assigneeEmail").and("status").is(status)));
    }

    @Override
    public List<Issue> getByProjectAndUserAndStatus(String projectName, String status, String assignedUserEmail) {
        return findAll(query(where("projectName").is(projectName).and("assigneeEmail").is(assignedUserEmail).and("status").is(status)));
    }

    @Override
    public List<Issue> getByProjectAndAssignedUser(String projectName, String assignedUserEmail) {
        return findAll(query(where("projectName").is(projectName).and("assigneeEmail").is(assignedUserEmail)));
    }

    @Override
    public int getCountByDateAndOpenedStatus(String projectName, Date date) {
        return findAll(query(where("projectName").is(projectName).and("status").is("OPENED").and("creationDate").is(date))).size();
    }

    @Override
    public int getCountByDateAndClosedStatus(String projectName, Date date) {
        return findAll(query(where("projectName").is(projectName).and("status").is("CLOSED").and("creationDate").is(date))).size();
    }
}
