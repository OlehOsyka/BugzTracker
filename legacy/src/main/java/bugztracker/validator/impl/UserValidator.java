package bugztracker.validator.impl;

import bugztracker.entity.User;
import bugztracker.exception.ValidationException;
import bugztracker.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Y. Vovk on 05.10.15.
 */
@Component
public class UserValidator implements IValidator<User> {

    @Autowired
    private Validator validator;

    @Override
    public void validate(User obj) {
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(obj);
        if (!constraintViolations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<User> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage());
            }
            throw new ValidationException(errors.toString());
        }
    }
}
