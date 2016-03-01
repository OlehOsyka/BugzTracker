package com.bugztracker.commons.validators.impl;

import com.bugztracker.commons.validators.ICommonsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
@Component
public class CommonsEntityValidator implements ICommonsValidator {

    @Autowired
    @Qualifier("entityValidator")
    private Validator validator;

    public void validate(Object obj) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        if (!constraintViolations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage());
            }
            throw new ValidationException(errors.toString());
        }
    }
}
