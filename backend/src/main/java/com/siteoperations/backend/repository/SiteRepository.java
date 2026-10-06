package com.siteoperations.backend.repository;

import com.siteoperations.backend.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {

    List<Site> findByStatusIgnoreCase(String status);

    List<Site> findByNameContainingIgnoreCase(String name);
}