package bugztracker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@Entity
@Table(name = "participant")
public class Participant implements Serializable {

    private User userParticipant;
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUserParticipant() {
        return userParticipant;
    }

    public void setUserParticipant(User userParticipant) {
        this.userParticipant = userParticipant;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        if (!userParticipant.equals(that.userParticipant)) return false;
        return project.equals(that.project);

    }

    @Override
    public int hashCode() {
        int result = userParticipant.hashCode();
        result = 31 * result + project.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "userParticipant=" + userParticipant +
                ", project=" + project +
                '}';
    }


}
