package com.siteoperations.backend.repository;

import com.siteoperations.backend.entity.Installation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstallationRepository extends JpaRepository<Installation, Long> {

    List<Installation> findByStatusIgnoreCase(String status);

    List<Installation> findBySiteId(Long siteId);
    
}