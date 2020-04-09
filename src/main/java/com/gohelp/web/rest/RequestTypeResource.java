package com.gohelp.web.rest;

import com.gohelp.service.RequestTypeService;
import com.gohelp.web.rest.errors.BadRequestAlertException;
import com.gohelp.service.dto.RequestTypeDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.gohelp.domain.RequestType}.
 */
@RestController
@RequestMapping("/api")
public class RequestTypeResource {

    private final Logger log = LoggerFactory.getLogger(RequestTypeResource.class);

    private static final String ENTITY_NAME = "requestType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestTypeService requestTypeService;

    public RequestTypeResource(RequestTypeService requestTypeService) {
        this.requestTypeService = requestTypeService;
    }

    /**
     * {@code POST  /request-types} : Create a new requestType.
     *
     * @param requestTypeDTO the requestTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestTypeDTO, or with status {@code 400 (Bad Request)} if the requestType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/request-types")
    public ResponseEntity<RequestTypeDTO> createRequestType(@RequestBody RequestTypeDTO requestTypeDTO) throws URISyntaxException {
        log.debug("REST request to save RequestType : {}", requestTypeDTO);
        if (requestTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequestTypeDTO result = requestTypeService.save(requestTypeDTO);
        return ResponseEntity.created(new URI("/api/request-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /request-types} : Updates an existing requestType.
     *
     * @param requestTypeDTO the requestTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestTypeDTO,
     * or with status {@code 400 (Bad Request)} if the requestTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/request-types")
    public ResponseEntity<RequestTypeDTO> updateRequestType(@RequestBody RequestTypeDTO requestTypeDTO) throws URISyntaxException {
        log.debug("REST request to update RequestType : {}", requestTypeDTO);
        if (requestTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RequestTypeDTO result = requestTypeService.save(requestTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /request-types} : get all the requestTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestTypes in body.
     */
    @GetMapping("/request-types")
    public ResponseEntity<List<RequestTypeDTO>> getAllRequestTypes(Pageable pageable) {
        log.debug("REST request to get a page of RequestTypes");
        Page<RequestTypeDTO> page = requestTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /request-types/:id} : get the "id" requestType.
     *
     * @param id the id of the requestTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/request-types/{id}")
    public ResponseEntity<RequestTypeDTO> getRequestType(@PathVariable Long id) {
        log.debug("REST request to get RequestType : {}", id);
        Optional<RequestTypeDTO> requestTypeDTO = requestTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestTypeDTO);
    }

    /**
     * {@code DELETE  /request-types/:id} : delete the "id" requestType.
     *
     * @param id the id of the requestTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/request-types/{id}")
    public ResponseEntity<Void> deleteRequestType(@PathVariable Long id) {
        log.debug("REST request to delete RequestType : {}", id);
        requestTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
