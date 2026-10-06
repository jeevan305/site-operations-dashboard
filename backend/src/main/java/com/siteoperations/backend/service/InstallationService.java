package com.siteoperations.backend.service;

import com.siteoperations.backend.dto.InstallationRequest;
import com.siteoperations.backend.dto.InstallationResponse;
import com.siteoperations.backend.entity.Installation;
import com.siteoperations.backend.entity.Site;
import com.siteoperations.backend.entity.User;
import com.siteoperations.backend.repository.InstallationRepository;
import com.siteoperations.backend.repository.SiteRepository;
import com.siteoperations.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.siteoperations.backend.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class InstallationService {

    private final InstallationRepository installationRepository;
    private final SiteRepository siteRepository;
    private final UserRepository userRepository;

    public InstallationService(InstallationRepository installationRepository,
                               SiteRepository siteRepository,
                               UserRepository userRepository) {
        this.installationRepository = installationRepository;
        this.siteRepository = siteRepository;
        this.userRepository = userRepository;
    }

    public List<InstallationResponse> getAllInstallations() {
        return installationRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public InstallationResponse getInstallationById(Long id) {
        Installation installation = installationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Installation not found with id: " + id));

        return toResponse(installation);
    }

    public InstallationResponse createInstallation(InstallationRequest request) {

        Site site = siteRepository.findById(request.getSiteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Site not found with id: " + request.getSiteId()));

        Installation installation = new Installation();

        installation.setSite(site);
        installation.setInstallationType(request.getInstallationType());
        installation.setStatus(request.getStatus());
        installation.setStartDate(request.getStartDate());
        installation.setCompletionDate(request.getCompletionDate());

        if (request.getTechnicianId() != null) {
            User technician = userRepository.findById(request.getTechnicianId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Technician not found with id: "
                                            + request.getTechnicianId()));

            installation.setTechnician(technician);
        }

        return toResponse(installationRepository.save(installation));
    }

    public InstallationResponse updateInstallation(
            Long id,
            InstallationRequest request) {

        Installation installation = installationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Installation not found with id: " + id));

        Site site = siteRepository.findById(request.getSiteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Site not found with id: " + request.getSiteId()));

        installation.setSite(site);
        installation.setInstallationType(request.getInstallationType());
        installation.setStatus(request.getStatus());
        installation.setStartDate(request.getStartDate());
        installation.setCompletionDate(request.getCompletionDate());

        if (request.getTechnicianId() != null) {
            User technician = userRepository.findById(request.getTechnicianId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Technician not found with id: "
                                            + request.getTechnicianId()));

            installation.setTechnician(technician);
        } else {
            installation.setTechnician(null);
        }

        return toResponse(installationRepository.save(installation));
    }
    
    public List<InstallationResponse> getByStatusAndSite(
            String status,
            Long siteId) {

        return installationRepository
                .findByStatusIgnoreCaseAndSiteId(status, siteId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void deleteInstallation(Long id) {

        if (!installationRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Installation not found with id: " + id);
        }

        installationRepository.deleteById(id);
    }

    public List<InstallationResponse> getByStatus(String status) {

        return installationRepository.findByStatusIgnoreCase(status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<InstallationResponse> getBySite(Long siteId) {

        return installationRepository.findBySiteId(siteId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private InstallationResponse toResponse(Installation installation) {

        Long technicianId = null;
        String technicianName = null;

        if (installation.getTechnician() != null) {
            technicianId = installation.getTechnician().getId();
            technicianName = installation.getTechnician().getName();
        }

        return new InstallationResponse(
                installation.getId(),
                installation.getSite().getId(),
                installation.getSite().getName(),
                installation.getInstallationType(),
                installation.getStatus(),
                technicianId,
                technicianName,
                installation.getStartDate(),
                installation.getCompletionDate()
        );
    }
}