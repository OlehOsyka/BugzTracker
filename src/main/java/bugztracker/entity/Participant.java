package bugztracker.entity;

import javax.persistence.*;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@Entity
@Table(name = "participant")
public class Participant {

    private long id;
    private User userParticipant;
    private Project project;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUserParticipant() {
        return userParticipant;
    }

    public void setUserParticipant(User userParticipant) {
        this.userParticipant = userParticipant;
    }

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Participant that = (Participant) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", userParticiant=" + userParticipant +
                ", project=" + project +
                '}';
    }


}
