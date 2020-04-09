package com.gohelp.service;

import com.gohelp.service.dto.RequestTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gohelp.domain.RequestType}.
 */
public interface RequestTypeService {

    /**
     * Save a requestType.
     *
     * @param requestTypeDTO the entity to save.
     * @return the persisted entity.
     */
    RequestTypeDTO save(RequestTypeDTO requestTypeDTO);

    /**
     * Get all the requestTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RequestTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" requestType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RequestTypeDTO> findOne(Long id);

    /**
     * Delete the "id" requestType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
