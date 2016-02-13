package bugztracker.bean;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Y. Vovk
 * Date: 02.10.15
 * Time: 13:05
 */
public class LoginBean implements Serializable {

    @NotBlank(message = "Login is required! ")
    private String email;

    @NotBlank(message = "Password is required! ")
    private String password;

    private boolean isRemember;

    public boolean isRemember() {
        return isRemember;
    }

    public void setIsRemember(boolean isRemember) {
        this.isRemember = isRemember;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
