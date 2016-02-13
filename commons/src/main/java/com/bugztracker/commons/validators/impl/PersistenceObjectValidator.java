package com.bugztracker.commons.validators.impl;

import com.bugztracker.commons.validators.IPersistenceObjectValidator;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Oleh_Osyka
 * Date: 13.02.2016
 * Time: 18:18
 */
@Component
public class PersistenceObjectValidator implements IPersistenceObjectValidator {

    @Autowired
    @Qualifier("persistenceValidator")
    private Validator validator;

    @Override
    public void validate(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!CollectionUtils.isEmpty(violations)) {
            throw new ConstraintViolationException("Invalid object. violations=" + violations, Sets.<ConstraintViolation<?>>newHashSet(violations));
        }
    }
}
