package com.bugztracker.persistence.dao;

import com.bugztracker.commons.entity.Comment;

import java.util.List;

/**
 * Created by Y. Vovk on 04.11.15.
 */
public interface ICommentRepository extends IBaseDao<Comment> {

    List<Comment> findSortedCommentsOfIssue(int issueId);

}
