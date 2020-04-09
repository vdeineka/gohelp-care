package com.gohelp.service.impl;

import com.gohelp.service.RequestTypeService;
import com.gohelp.domain.RequestType;
import com.gohelp.repository.RequestTypeRepository;
import com.gohelp.service.dto.RequestTypeDTO;
import com.gohelp.service.mapper.RequestTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RequestType}.
 */
@Service
@Transactional
public class RequestTypeServiceImpl implements RequestTypeService {

    private final Logger log = LoggerFactory.getLogger(RequestTypeServiceImpl.class);

    private final RequestTypeRepository requestTypeRepository;

    private final RequestTypeMapper requestTypeMapper;

    public RequestTypeServiceImpl(RequestTypeRepository requestTypeRepository, RequestTypeMapper requestTypeMapper) {
        this.requestTypeRepository = requestTypeRepository;
        this.requestTypeMapper = requestTypeMapper;
    }

    /**
     * Save a requestType.
     *
     * @param requestTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RequestTypeDTO save(RequestTypeDTO requestTypeDTO) {
        log.debug("Request to save RequestType : {}", requestTypeDTO);
        RequestType requestType = requestTypeMapper.toEntity(requestTypeDTO);
        requestType = requestTypeRepository.save(requestType);
        return requestTypeMapper.toDto(requestType);
    }

    /**
     * Get all the requestTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RequestTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestTypes");
        return requestTypeRepository.findAll(pageable)
            .map(requestTypeMapper::toDto);
    }

    /**
     * Get one requestType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RequestTypeDTO> findOne(Long id) {
        log.debug("Request to get RequestType : {}", id);
        return requestTypeRepository.findById(id)
            .map(requestTypeMapper::toDto);
    }

    /**
     * Delete the requestType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RequestType : {}", id);
        requestTypeRepository.deleteById(id);
    }
}
