package bugztracker.validator;

import bugztracker.bean.LoginBean;
import bugztracker.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Y. Vovk on 23.09.15.
 */
@Component
public class LoginBeanValidator implements IValidator<LoginBean> {

    @Autowired
    private Validator validator;

    public void validate(LoginBean obj) {
        Set<ConstraintViolation<LoginBean>> constraintViolations = validator.validate(obj);
        if (!constraintViolations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<LoginBean> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage());
            }
            throw new ValidationException(errors.toString());
        }
    }

}
