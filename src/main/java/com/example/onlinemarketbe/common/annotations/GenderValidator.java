package com.example.onlinemarketbe.common.annotations;

import com.example.onlinemarketbe.common.enums.GenderEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null || s.isEmpty()) return false;
        return (s.equals(GenderEnum.MALE.toString()) || s.equals(GenderEnum.FEMALE.toString()));
    }
}
