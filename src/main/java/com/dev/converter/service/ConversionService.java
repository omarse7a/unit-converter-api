package com.dev.converter.service;

import com.dev.converter.enums.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversionService {
    public List<Category> getCategories() {
        return new ArrayList<>(List.of(Category.values()));
    }

    public List<Unit> getUnits(String cat) {
        if(cat.toUpperCase().equals(Category.TEMPERATURE.name()) ) {
            return new ArrayList<>(List.of(TemperatureUnit.values()));
        }
        else if(cat.toUpperCase().equals(Category.LENGTH.name()) ) {
            return new ArrayList<>(List.of(LengthUnit.values()));
        }
        else if(cat.toUpperCase().equals(Category.WEIGHT.name()) ) {
            return new ArrayList<>(List.of(WeightUnit.values()));
        }
        else if(cat.toUpperCase().equals(Category.TIME.name()) ) {
            return new ArrayList<>(List.of(TimeUnit.values()));
        }
        throw new IllegalArgumentException("No units found for category: " + cat);
    }
}
