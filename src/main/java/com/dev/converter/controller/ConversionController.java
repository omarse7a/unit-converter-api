package com.dev.converter.controller;

import com.dev.converter.dto.ConversionRequest;
import com.dev.converter.dto.ConversionResponse;
import com.dev.converter.enums.Category;
import com.dev.converter.enums.Unit;
import com.dev.converter.exception.InvalidUnitException;
import com.dev.converter.exception.UnsupportedCategoryException;
import com.dev.converter.service.ConversionService;
import com.dev.converter.service.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Unit Converter", description = "Unit conversion API, supporting temperature, length, weight and time unit conversions")
@RestController
@RequestMapping("/api")
public class ConversionController {
    private final ConversionService conversionService;
    private final InfoService infoService;

    @Autowired
    public ConversionController(ConversionService conversionService, InfoService infoService) {
        this.conversionService = conversionService;
        this.infoService = infoService;
    }

    @Operation(
            summary = "Convert Units",
            description = "Convert a value from one measurement unit to another based on the category"
    )
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

    @Operation(
            summary = "Fetch Supported Categories",
            description = "Returns a list of supported measurement categories"
    )
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getSupportedCategories() {
        List<Category> categories = infoService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @Operation(
            summary = "Fetch Supported Units",
            description = "Returns a list of supported measurement units for a category"
    )
    @GetMapping("/units")
    public ResponseEntity<?> getSupportedUnits(@RequestParam String category) {
        try {
            List<Unit> units = infoService.getUnits(category);
            return ResponseEntity.status(200).body(units);
        }
        catch (UnsupportedCategoryException e) {
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }

    @Operation(
            summary = "Sample Payload",
            description = "Returns a sample for /convert payload"
    )
    @GetMapping("/sample-payload")
    public ResponseEntity<ConversionRequest> getSamplePayload() {
        ConversionRequest sample = infoService.getSamplePayload();
        return ResponseEntity.status(200).body(sample);
    }

    @Operation(
            summary = "Health",
            description = "Returns system status"
    )
    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        String status = infoService.getHealth();
        return ResponseEntity.status(200).body(Map.of("status", status));
    }
}
