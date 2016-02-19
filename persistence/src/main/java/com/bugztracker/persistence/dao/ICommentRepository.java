package com.bugztracker.persistence.dao;

import com.bugztracker.commons.entity.Comment;

import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
public interface ICommentRepository extends IBaseDao<Comment> {

    List<Comment> findSortedCommentsOfIssue(int issueId);

}
