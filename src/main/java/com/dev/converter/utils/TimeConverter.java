package com.dev.converter.utils;

import com.dev.converter.dto.ConversionRequest;
import com.dev.converter.dto.ConversionResponse;
import com.dev.converter.enums.TimeUnit;
import com.dev.converter.enums.Unit;
import com.dev.converter.exception.InvalidUnitException;
import org.springframework.stereotype.Component;

@Component
public class TimeConverter implements UnitConverter{

    @Override
    public Unit validateUnit(String unit) {
        try {
            return TimeUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid unit for time category: " + unit);
        }
    }

    @Override
    public ConversionResponse convert(ConversionRequest request) {
        TimeUnit from = (TimeUnit) validateUnit(request.getFromUnit());
        TimeUnit to = (TimeUnit) validateUnit(request.getToUnit());
        double val = request.getValue();

        double seconds = switch(from) {
            case SECONDS -> val;
            case MINUTES -> val * 60;
            case HOURS -> val * 3600;
            case DAYS -> val * 3600 * 24;
        };

        double result = switch(to) {
            case SECONDS -> seconds;
            case MINUTES -> seconds / 60;
            case HOURS -> seconds / 3600;
            case DAYS -> seconds / (3600 * 24);
        };
        return new ConversionResponse(result, "success");
    }
}
