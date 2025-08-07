package com.dev.converter.service;

import com.dev.converter.dto.ConversionRequest;
import com.dev.converter.dto.ConversionResponse;
import com.dev.converter.enums.*;
import com.dev.converter.exception.InvalidUnitException;
import com.dev.converter.exception.UnsupportedCategoryException;
import com.dev.converter.utils.TemperatureConverter;
import com.dev.converter.utils.UnitConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConversionService {

    private final Map<Category, UnitConverter> converters;

    public ConversionService(TemperatureConverter t) {
        converters = new HashMap<>();
        converters.put(Category.TEMPERATURE, t);
    }

    public ConversionResponse convertUnits(ConversionRequest request) {
        try {
            Category category = Category.valueOf(request.getCategory().toUpperCase());
            UnitConverter converter = converters.get(category);
            return converter.convert(request);
        } catch (IllegalArgumentException e) {
            throw new UnsupportedCategoryException("Unsupported category: " + request.getCategory());
        }
    }

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
