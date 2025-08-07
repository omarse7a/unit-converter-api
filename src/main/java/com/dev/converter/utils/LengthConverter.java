package com.dev.converter.utils;

import com.dev.converter.dto.ConversionRequest;
import com.dev.converter.dto.ConversionResponse;
import com.dev.converter.enums.LengthUnit;
import com.dev.converter.enums.Unit;
import com.dev.converter.exception.InvalidUnitException;
import org.springframework.stereotype.Component;

@Component
public class LengthConverter implements UnitConverter{

    @Override
    public Unit validateUnit(String unit) {
        try {
            return LengthUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid unit for length category: " + unit);
        }
    }

    @Override
    public ConversionResponse convert(ConversionRequest request) {
        LengthUnit from = (LengthUnit) validateUnit(request.getFromUnit());
        LengthUnit to = (LengthUnit) validateUnit(request.getToUnit());
        double val = request.getValue();

        double meter = switch(from) {
            case METER -> val;
            case KILOMETER -> val * 1000;
            case MILE -> val * 1609.34;
            case INCH -> val / 39.37;
            case FOOT -> val / 3.28;
        };

        double result = switch(to) {
            case METER -> meter;
            case KILOMETER -> meter / 1000;
            case MILE -> meter / 1609.34;
            case INCH -> meter * 39.37;
            case FOOT -> meter * 3.28;
        };
        return new ConversionResponse(result, "success");
    }
}
