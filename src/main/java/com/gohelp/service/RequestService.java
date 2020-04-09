package com.gohelp.service;

import com.gohelp.service.dto.RequestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gohelp.domain.Request}.
 */
public interface RequestService {

    /**
     * Save a request.
     *
     * @param requestDTO the entity to save.
     * @return the persisted entity.
     */
    RequestDTO save(RequestDTO requestDTO);

    /**
     * Get all the requests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RequestDTO> findAll(Pageable pageable);

    /**
     * Get the "id" request.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RequestDTO> findOne(Long id);

    /**
     * Delete the "id" request.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
