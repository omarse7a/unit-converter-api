package com.dev.converter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversionRequest {
    private String category;
    private String fromUnit;
    private String toUnit;
    private double value;
}
