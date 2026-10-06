package com.siteoperations.backend.service;

import com.siteoperations.backend.dto.SiteRequest;
import com.siteoperations.backend.dto.SiteResponse;
import com.siteoperations.backend.entity.Site;
import com.siteoperations.backend.entity.User;
import com.siteoperations.backend.repository.SiteRepository;
import com.siteoperations.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.siteoperations.backend.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class SiteService {

    private final SiteRepository siteRepository;
    private final UserRepository userRepository;

    public SiteService(SiteRepository siteRepository,
                       UserRepository userRepository) {
        this.siteRepository = siteRepository;
        this.userRepository = userRepository;
    }

    public List<SiteResponse> getAllSites() {
        return siteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SiteResponse getSiteById(Long id) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site not found with id: " + id));

        return toResponse(site);
    }

    public SiteResponse createSite(SiteRequest request) {

        Site site = new Site();
        site.setName(request.getName());
        site.setLocation(request.getLocation());
        site.setStatus(request.getStatus());

        if (request.getCreatedBy() != null) {
            User user = userRepository.findById(request.getCreatedBy())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "User not found with id: " + request.getCreatedBy()));

            site.setCreatedBy(user);
        }

        return toResponse(siteRepository.save(site));
    }

    public SiteResponse updateSite(Long id, SiteRequest request) {

        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site not found with id: " + id));

        site.setName(request.getName());
        site.setLocation(request.getLocation());
        site.setStatus(request.getStatus());

        if (request.getCreatedBy() != null) {
            User user = userRepository.findById(request.getCreatedBy())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "User not found with id: " + request.getCreatedBy()));

            site.setCreatedBy(user);
        }

        return toResponse(siteRepository.save(site));
    }

    public void deleteSite(Long id) {

        if (!siteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Site not found with id: " + id);
        }

        siteRepository.deleteById(id);
    }

    public List<SiteResponse> searchSites(String name, String status) {

        List<Site> sites;

        if (name != null && !name.isBlank()
                && status != null && !status.isBlank()) {

            sites = siteRepository
                    .findByNameContainingIgnoreCaseAndStatusIgnoreCase(name, status);

        } else if (name != null && !name.isBlank()) {

            sites = siteRepository.findByNameContainingIgnoreCase(name);

        } else if (status != null && !status.isBlank()) {

            sites = siteRepository.findByStatusIgnoreCase(status);

        } else {

            sites = siteRepository.findAll();
        }

        return sites.stream()
                .map(this::toResponse)
                .toList();
    }

    private SiteResponse toResponse(Site site) {

        Long createdBy = null;
        String createdByName = null;

        if (site.getCreatedBy() != null) {
            createdBy = site.getCreatedBy().getId();
            createdByName = site.getCreatedBy().getName();
        }

        return new SiteResponse(
                site.getId(),
                site.getName(),
                site.getLocation(),
                site.getStatus(),
                createdBy,
                createdByName
        );
    }
}