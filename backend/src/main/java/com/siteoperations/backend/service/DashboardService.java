package com.siteoperations.backend.service;

import com.siteoperations.backend.dto.DashboardSummaryResponse;
import com.siteoperations.backend.repository.InstallationRepository;
import com.siteoperations.backend.repository.SiteRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final SiteRepository siteRepository;
    private final InstallationRepository installationRepository;

    public DashboardService(SiteRepository siteRepository,
                            InstallationRepository installationRepository) {
        this.siteRepository = siteRepository;
        this.installationRepository = installationRepository;
    }

    public DashboardSummaryResponse getSummary() {

        long totalSites = siteRepository.count();

        long totalInstallations = installationRepository.count();

        long completedInstallations =
                installationRepository.findByStatusIgnoreCase("COMPLETED").size();

        long inProgressInstallations =
                installationRepository.findByStatusIgnoreCase("IN_PROGRESS").size();

        long plannedInstallations =
                installationRepository.findByStatusIgnoreCase("PLANNED").size();

        return new DashboardSummaryResponse(
                totalSites,
                totalInstallations,
                completedInstallations,
                inProgressInstallations,
                plannedInstallations
        );
    }
}