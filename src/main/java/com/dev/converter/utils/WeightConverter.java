package com.dev.converter.utils;

import com.dev.converter.dto.ConversionRequest;
import com.dev.converter.dto.ConversionResponse;
import com.dev.converter.enums.Unit;
import com.dev.converter.enums.WeightUnit;
import com.dev.converter.exception.InvalidUnitException;
import org.springframework.stereotype.Component;

@Component
public class WeightConverter implements UnitConverter{

    @Override
    public Unit validateUnit(String unit) {
        try {
            return WeightUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid unit for weight category: " + unit);
        }
    }

    @Override
    public ConversionResponse convert(ConversionRequest request) {
        WeightUnit from = (WeightUnit) validateUnit(request.getFromUnit());
        WeightUnit to = (WeightUnit) validateUnit(request.getToUnit());
        double val = request.getValue();

        double gram = switch(from) {
            case GRAM -> val;
            case KILOGRAM -> val * 1000;
            case OUNCE -> val * 28.35;
            case POUND -> val * 453.6;
        };

        double result = switch(to) {
            case GRAM -> gram;
            case KILOGRAM -> gram / 1000;
            case OUNCE -> gram / 28.35;
            case POUND -> gram / 453.6;
        };
        return new ConversionResponse(result, "success");
    }
}
