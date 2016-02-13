package bugztracker.validator.impl;

import bugztracker.entity.IssueComment;
import bugztracker.exception.ValidationException;
import bugztracker.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Yuliia Vovk
 * 19.11.15
 */
@Component
public class IssueCommentValidator implements IValidator<IssueComment> {

    @Autowired
    private Validator validator;

    @Override
    public void validate(IssueComment obj) {
        Set<ConstraintViolation<IssueComment>> constraintViolations = validator.validate(obj);
        if (!constraintViolations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<IssueComment> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage());
            }
            throw new ValidationException(errors.toString());
        }
    }
}
