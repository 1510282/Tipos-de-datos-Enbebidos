package com.ingeniero.criteria.converter;

import jakarta.persistence.AttributeConverter;
import com.ingeniero.criteria.model.EmployeeCategory;

public class CategoryConverter implements AttributeConverter<EmployeeCategory, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmployeeCategory attribute) {
        return switch (attribute) {
            case JUNIOR -> 1;
            case SENIOR -> 2;
            case MANAGER -> 3;
            case C_LEVEL -> 4;
        };
    }

    @Override
    public EmployeeCategory convertToEntityAttribute(Integer dbData) {
        return switch (dbData) {
            case 1 -> EmployeeCategory.JUNIOR;
            case 2 -> EmployeeCategory.SENIOR;
            case 3 -> EmployeeCategory.MANAGER;
            case 4 -> EmployeeCategory.C_LEVEL;
            default -> throw new IllegalArgumentException("Unknown value: " + dbData);
        };
    }
}