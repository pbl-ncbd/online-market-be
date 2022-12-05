package com.example.onlinemarketbe.common.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGender {
    String message() default "Invalid gender string";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
