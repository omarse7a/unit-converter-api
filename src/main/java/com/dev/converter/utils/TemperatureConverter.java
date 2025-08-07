package com.dev.converter.utils;

import com.dev.converter.dto.ConversionRequest;
import com.dev.converter.dto.ConversionResponse;
import com.dev.converter.enums.TemperatureUnit;
import com.dev.converter.enums.Unit;
import com.dev.converter.exception.InvalidUnitException;
import org.springframework.stereotype.Component;

@Component
public class TemperatureConverter implements UnitConverter {
    @Override
    public Unit validateUnit(String unit) {
        try {
            return TemperatureUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Invalid unit for temperature category: " + unit);
        }
    }

    /* convert the fromUnit to Celsius, then convert the Celsius value to the toUnit */
    @Override
    public ConversionResponse convert(ConversionRequest request) {
        TemperatureUnit from = (TemperatureUnit) validateUnit(request.getFromUnit());
        TemperatureUnit to = (TemperatureUnit) validateUnit(request.getToUnit());
        double value = request.getValue();

        double celsius = switch(from) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value - 32) * 5 / 9;
            case KELVIN -> value - 273.15;
        };

        double result = switch(to) {
            case CELSIUS -> celsius;
            case FAHRENHEIT -> celsius * 9 / 5 + 32;
            case KELVIN -> celsius + 273.15;
        };
        return new ConversionResponse(result, "success");
    }
}
