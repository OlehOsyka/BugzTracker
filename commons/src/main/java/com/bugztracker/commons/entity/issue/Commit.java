package com.bugztracker.commons.entity.issue;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: Y. Vovk
 * 12.02.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Commit implements Serializable {

    private String commitHash;
    private Date date;

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Commit commit = (Commit) o;

        return new EqualsBuilder()
                .append(commitHash, commit.commitHash)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(commitHash)
                .toHashCode();
    }
}
