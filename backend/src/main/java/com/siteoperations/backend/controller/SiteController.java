package com.siteoperations.backend.controller;

import com.siteoperations.backend.dto.SiteRequest;
import com.siteoperations.backend.dto.SiteResponse;
import com.siteoperations.backend.service.SiteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/sites")
@CrossOrigin(origins = "*")
public class SiteController {
	
	private static final Logger logger =
	        LoggerFactory.getLogger(SiteController.class);

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping
    public ResponseEntity<List<SiteResponse>> getSites(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status) {

        return ResponseEntity.ok(
                siteService.searchSites(name, status)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiteResponse> getSiteById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                siteService.getSiteById(id)
        );
    }

    @PostMapping
    public ResponseEntity<SiteResponse> createSite(
            @Valid @RequestBody SiteRequest request) {
    	
    	logger.info("Creating new site: {}", request.getName());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(siteService.createSite(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiteResponse> updateSite(
            @PathVariable Long id,
            @Valid @RequestBody SiteRequest request) {

        return ResponseEntity.ok(
                siteService.updateSite(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(
            @PathVariable Long id) {

        siteService.deleteSite(id);

        return ResponseEntity.noContent().build();
    }
}