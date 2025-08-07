package com.dev.converter.utils;

import com.dev.converter.dto.ConversionRequest;
import com.dev.converter.dto.ConversionResponse;
import com.dev.converter.enums.Unit;

public interface UnitConverter {
    Unit validateUnit(String unit);
    ConversionResponse convert(ConversionRequest request);
}
