package com.gohelp.service.impl;

import com.gohelp.service.GeoJsonPointService;
import com.gohelp.domain.GeoJsonPoint;
import com.gohelp.repository.GeoJsonPointRepository;
import com.gohelp.service.dto.GeoJsonPointDTO;
import com.gohelp.service.mapper.GeoJsonPointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoJsonPoint}.
 */
@Service
@Transactional
public class GeoJsonPointServiceImpl implements GeoJsonPointService {

    private final Logger log = LoggerFactory.getLogger(GeoJsonPointServiceImpl.class);

    private final GeoJsonPointRepository geoJsonPointRepository;

    private final GeoJsonPointMapper geoJsonPointMapper;

    public GeoJsonPointServiceImpl(GeoJsonPointRepository geoJsonPointRepository, GeoJsonPointMapper geoJsonPointMapper) {
        this.geoJsonPointRepository = geoJsonPointRepository;
        this.geoJsonPointMapper = geoJsonPointMapper;
    }

    /**
     * Save a geoJsonPoint.
     *
     * @param geoJsonPointDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GeoJsonPointDTO save(GeoJsonPointDTO geoJsonPointDTO) {
        log.debug("Request to save GeoJsonPoint : {}", geoJsonPointDTO);
        GeoJsonPoint geoJsonPoint = geoJsonPointMapper.toEntity(geoJsonPointDTO);
        geoJsonPoint = geoJsonPointRepository.save(geoJsonPoint);
        return geoJsonPointMapper.toDto(geoJsonPoint);
    }

    /**
     * Get all the geoJsonPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GeoJsonPointDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoJsonPoints");
        return geoJsonPointRepository.findAll(pageable)
            .map(geoJsonPointMapper::toDto);
    }

    /**
     * Get one geoJsonPoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GeoJsonPointDTO> findOne(Long id) {
        log.debug("Request to get GeoJsonPoint : {}", id);
        return geoJsonPointRepository.findById(id)
            .map(geoJsonPointMapper::toDto);
    }

    /**
     * Delete the geoJsonPoint by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GeoJsonPoint : {}", id);
        geoJsonPointRepository.deleteById(id);
    }
}
