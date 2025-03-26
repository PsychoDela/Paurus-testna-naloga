package com.paurus.taxservice.controller;

import com.paurus.taxservice.model.TaxRequest;
import com.paurus.taxservice.model.TaxResponse;
import com.paurus.taxservice.service.TaxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tax")
public class TaxController {

    private final TaxService service;

    public TaxController(TaxService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaxResponse> calculateTax(@RequestBody TaxRequest req) {
        return ResponseEntity.ok(service.calculateTax(req));
    }
}

