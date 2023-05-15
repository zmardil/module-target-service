package com.ihub.shiftservice.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumConstraint, Enum<?>> {
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumConstraint constraintAnnotation) {
        enumClass = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (Enum<?> enumValue : enumClass.getEnumConstants()) {
            if (value.equals(enumValue)) {
                return true;
            }
        }
        return false;
    }
}
