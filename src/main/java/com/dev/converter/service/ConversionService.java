package com.dev.converter.service;

import com.dev.converter.dto.ConversionRequest;
import com.dev.converter.dto.ConversionResponse;
import com.dev.converter.enums.*;
import com.dev.converter.exception.UnsupportedCategoryException;
import com.dev.converter.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConversionService {

    private final Map<Category, UnitConverter> converters;

    @Autowired
    public ConversionService(TemperatureConverter temp, LengthConverter l, WeightConverter w, TimeConverter t) {
        converters = new HashMap<>();
        converters.put(Category.TEMPERATURE, temp);
        converters.put(Category.LENGTH, l);
        converters.put(Category.WEIGHT, w);
        converters.put(Category.TIME, t);
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
}
