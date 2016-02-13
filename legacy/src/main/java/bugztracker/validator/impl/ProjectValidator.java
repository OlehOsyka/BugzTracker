package bugztracker.validator.impl;

import bugztracker.entity.Project;
import bugztracker.exception.ValidationException;
import bugztracker.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Y. Vovk on 20.10.15.
 */
@Component
public class ProjectValidator implements IValidator<Project> {

    @Autowired
    private Validator validator;

    @Override
    public void validate(Project obj) {
        Set<ConstraintViolation<Project>> constraintViolations = validator.validate(obj);
        if (!constraintViolations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<Project> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage());
            }
            throw new ValidationException(errors.toString());
        }
    }
}
