package com.siteoperations.backend.dto;

public class DashboardSummaryResponse {

    private long totalSites;
    private long totalInstallations;
    private long completedInstallations;
    private long inProgressInstallations;
    private long plannedInstallations;

    public DashboardSummaryResponse(long totalSites,
                                     long totalInstallations,
                                     long completedInstallations,
                                     long inProgressInstallations,
                                     long plannedInstallations) {
        this.totalSites = totalSites;
        this.totalInstallations = totalInstallations;
        this.completedInstallations = completedInstallations;
        this.inProgressInstallations = inProgressInstallations;
        this.plannedInstallations = plannedInstallations;
    }

    public long getTotalSites() {
        return totalSites;
    }

    public long getTotalInstallations() {
        return totalInstallations;
    }

    public long getCompletedInstallations() {
        return completedInstallations;
    }

    public long getInProgressInstallations() {
        return inProgressInstallations;
    }

    public long getPlannedInstallations() {
        return plannedInstallations;
    }
}