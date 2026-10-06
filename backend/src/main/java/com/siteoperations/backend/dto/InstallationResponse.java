package com.siteoperations.backend.dto;

import java.time.LocalDate;

public class InstallationResponse {

    private Long id;
    private Long siteId;
    private String siteName;
    private String installationType;
    private String status;
    private Long technicianId;
    private String technicianName;
    private LocalDate startDate;
    private LocalDate completionDate;

    public InstallationResponse() {
    }

    public InstallationResponse(Long id, Long siteId, String siteName,
                                String installationType, String status,
                                Long technicianId, String technicianName,
                                LocalDate startDate,
                                LocalDate completionDate) {
        this.id = id;
        this.siteId = siteId;
        this.siteName = siteName;
        this.installationType = installationType;
        this.status = status;
        this.technicianId = technicianId;
        this.technicianName = technicianName;
        this.startDate = startDate;
        this.completionDate = completionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getInstallationType() {
        return installationType;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Long technicianId) {
        this.technicianId = technicianId;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}