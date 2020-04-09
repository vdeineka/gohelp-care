package com.gohelp.service;

import com.gohelp.service.dto.ProposalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gohelp.domain.Proposal}.
 */
public interface ProposalService {

    /**
     * Save a proposal.
     *
     * @param proposalDTO the entity to save.
     * @return the persisted entity.
     */
    ProposalDTO save(ProposalDTO proposalDTO);

    /**
     * Get all the proposals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProposalDTO> findAll(Pageable pageable);

    /**
     * Get the "id" proposal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProposalDTO> findOne(Long id);

    /**
     * Delete the "id" proposal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
