package com.bugztracker.persistence.dao.impl;

import com.bugztracker.commons.entity.Comment;
import com.bugztracker.persistence.dao.BaseDao;
import com.bugztracker.persistence.dao.ICommentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by Oleh_Osyka
 * Date: 14.02.2016
 * Time: 17:29
 */
@Repository
public class CommentRepository extends BaseDao<Comment> implements ICommentRepository {

    public static final String COLLECTION_NAME = "Comments";

    protected CommentRepository() {
        super(COLLECTION_NAME, Comment.class);
    }

    @Override
    protected void createIndex() {
        createIndex(new Index("issueId", Sort.Direction.ASC).on("date", Sort.Direction.ASC).sparse());
    }

    @Override
    public List<Comment> findSortedCommentsOfIssue(int issueId) {
        return findAll(query(where("issueId").is(issueId)).with(new Sort(Sort.Direction.ASC, "date")));
    }

    //todo find by list<comentids>
}
