package com.dev.converter.utils;

import com.dev.converter.dto.ConversionRequest;
import com.dev.converter.dto.ConversionResponse;
import com.dev.converter.enums.Unit;

public interface UnitConverter {
    // validate whether the unit exist or not
    Unit validateUnit(String unit);

    // convert the fromUnit to the standard unit for the category, then convert the gram value to the toUnit
    ConversionResponse convert(ConversionRequest request);
}
