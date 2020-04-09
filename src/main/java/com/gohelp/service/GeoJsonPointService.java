package com.gohelp.service;

import com.gohelp.service.dto.GeoJsonPointDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gohelp.domain.GeoJsonPoint}.
 */
public interface GeoJsonPointService {

    /**
     * Save a geoJsonPoint.
     *
     * @param geoJsonPointDTO the entity to save.
     * @return the persisted entity.
     */
    GeoJsonPointDTO save(GeoJsonPointDTO geoJsonPointDTO);

    /**
     * Get all the geoJsonPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GeoJsonPointDTO> findAll(Pageable pageable);

    /**
     * Get the "id" geoJsonPoint.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GeoJsonPointDTO> findOne(Long id);

    /**
     * Delete the "id" geoJsonPoint.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
