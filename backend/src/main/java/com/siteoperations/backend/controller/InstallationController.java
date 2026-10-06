package com.siteoperations.backend.controller;

import com.siteoperations.backend.dto.InstallationRequest;
import com.siteoperations.backend.dto.InstallationResponse;
import com.siteoperations.backend.service.InstallationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/installations")
@CrossOrigin(origins = "*")
public class InstallationController {

    private final InstallationService installationService;

    public InstallationController(InstallationService installationService) {
        this.installationService = installationService;
    }

    @GetMapping
    public ResponseEntity<List<InstallationResponse>> getInstallations(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long siteId) {

        if (status != null && !status.isBlank()) {
            return ResponseEntity.ok(
                    installationService.getByStatus(status)
            );
        }

        if (siteId != null) {
            return ResponseEntity.ok(
                    installationService.getBySite(siteId)
            );
        }

        return ResponseEntity.ok(
                installationService.getAllInstallations()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstallationResponse> getInstallationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                installationService.getInstallationById(id)
        );
    }

    @PostMapping
    public ResponseEntity<InstallationResponse> createInstallation(
            @Valid @RequestBody InstallationRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(installationService.createInstallation(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstallationResponse> updateInstallation(
            @PathVariable Long id,
            @Valid @RequestBody InstallationRequest request) {

        return ResponseEntity.ok(
                installationService.updateInstallation(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstallation(
            @PathVariable Long id) {

        installationService.deleteInstallation(id);

        return ResponseEntity.noContent().build();
    }
}