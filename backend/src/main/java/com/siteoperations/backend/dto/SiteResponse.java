package com.siteoperations.backend.dto;

public class SiteResponse {

    private Long id;
    private String name;
    private String location;
    private String status;
    private Long createdBy;
    private String createdByName;

    public SiteResponse() {
    }

    public SiteResponse(Long id, String name, String location,
                        String status, Long createdBy,
                        String createdByName) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = status;
        this.createdBy = createdBy;
        this.createdByName = createdByName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }
}