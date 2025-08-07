package com.dev.converter.service;

import com.dev.converter.enums.*;
import com.dev.converter.exception.UnsupportedCategoryException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoService {
    public List<Category> getCategories() {
        return new ArrayList<>(List.of(Category.values()));
    }

    public List<Unit> getUnits(String cat) {
        try {
            Category category = Category.valueOf(cat.toUpperCase());
            return switch(category) {
                case TEMPERATURE -> List.of(TemperatureUnit.values());
                case LENGTH -> List.of(LengthUnit.values());
                case WEIGHT -> List.of(WeightUnit.values());
                case TIME -> List.of(TimeUnit.values());
            };
        } catch (IllegalArgumentException e) {
            throw new UnsupportedCategoryException("Unsupported category: " + cat);
        }
    }
}
