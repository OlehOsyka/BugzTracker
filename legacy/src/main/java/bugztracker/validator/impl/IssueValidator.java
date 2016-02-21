package bugztracker.validator.impl;

import bugztracker.entity.Issue;
import bugztracker.exception.ValidationException;
import bugztracker.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
@Component
public class IssueValidator implements IValidator<Issue> {

    @Autowired
    private Validator validator;

    @Override
    public void validate(Issue obj) {
        Set<ConstraintViolation<Issue>> constraintViolations = validator.validate(obj);
        if (!constraintViolations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<Issue> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage());
            }
            throw new ValidationException(errors.toString());
        }
    }
}
