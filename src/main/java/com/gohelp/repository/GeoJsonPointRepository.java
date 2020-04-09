package com.gohelp.repository;

import com.gohelp.domain.GeoJsonPoint;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeoJsonPoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoJsonPointRepository extends JpaRepository<GeoJsonPoint, Long> {
}
