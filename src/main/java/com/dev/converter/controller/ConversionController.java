package com.dev.converter.controller;

import com.dev.converter.dto.ConversionRequest;
import com.dev.converter.dto.ConversionResponse;
import com.dev.converter.enums.Category;
import com.dev.converter.enums.Unit;
import com.dev.converter.exception.InvalidUnitException;
import com.dev.converter.exception.UnsupportedCategoryException;
import com.dev.converter.service.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ConversionController {
    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /* Converts a value from one measurement unit to another based on the category */
    @PostMapping("/convert")
    public ResponseEntity<?> convert(@RequestBody ConversionRequest request) {
        try {
            ConversionResponse response = conversionService.convertUnits(request);
            return ResponseEntity.status(200).body(response);
        }
        catch (UnsupportedCategoryException | InvalidUnitException e) {
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }

    }

    /* Returns a list of supported measurement categories */
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getSupportedCategories() {
        List<Category> categories = conversionService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }

    /* Returns a list of supported measurement units for a category */
    @GetMapping("/units")
    public ResponseEntity<?> getSupportedUnits(@RequestParam String category) {
        try {
            List<Unit> units = conversionService.getUnits(category);
            return ResponseEntity.status(200).body(units);
        }
        catch (UnsupportedCategoryException e) {
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }
}
