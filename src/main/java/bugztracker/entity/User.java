package bugztracker.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@Entity
@Table(name = "user")
public class User {

    private long id;
    private String fullName;
    private String password;
    private String email;
    private Timestamp dateExpired;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "full_name", nullable = false)
    @NotBlank(message = "Full name is required! ")
    @Size(max = 100, message = "Please, shorten your full name to 100 symbols! ")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(nullable = false)
    @NotBlank(message = "Password is required! ")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is required! ")
    @Size(max = 50, message = "Please, use email 50 symbols length! ")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_expired")
    public Timestamp getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Timestamp dateExpired) {
        this.dateExpired = dateExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateExpired=" + dateExpired +
                '}';
    }


}
