package com.siteoperations.backend.controller;

import com.siteoperations.backend.dto.DashboardSummaryResponse;
import com.siteoperations.backend.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryResponse> getSummary() {

        return ResponseEntity.ok(
                dashboardService.getSummary()
        );
    }
}