package com.bugztracker.commons.beans.issue;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Y. Vovk
 * Date: 02.10.15
 * Time: 0:01
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Issue implements Serializable {

    @Id
    private int id;

    @NotBlank(message = "Name is required! ")
    @Size(max = 300, message = "Please, shorten the name of issue. Not more than 300 symbols is possible!")
    private String name;

    @Digits(integer = 2, fraction = 1, message = "Version must be a float number from 1 to 10! ")
    @Range(min = 1, max = 10, message = "Version must be a float number from 1 to 10! ")
    @NotNull(message = "Version is required and must be a float number from 1 to 10!")
    private Double version;
    private Date creationDate;
    private Date lastUpdateDate;
    private Priority priority;
    private Status status;
    private String description;
    private Category category;
    @NotBlank(message = "Creator is required! ")
    private String creatorEmail;
    @NotBlank(message = "Assignee is required! ")
    private String assigneeEmail;
    private String testerEmail;
    private String projectName;
    // attachmentPath
    private List<String> attachmentPaths;
    private List<Commit> commits;
    private List<String> commentIds;


}
