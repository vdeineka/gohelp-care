package com.gohelp.web.rest;

import com.gohelp.service.GeoJsonPointService;
import com.gohelp.web.rest.errors.BadRequestAlertException;
import com.gohelp.service.dto.GeoJsonPointDTO;

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
 * REST controller for managing {@link com.gohelp.domain.GeoJsonPoint}.
 */
@RestController
@RequestMapping("/api")
public class GeoJsonPointResource {

    private final Logger log = LoggerFactory.getLogger(GeoJsonPointResource.class);

    private static final String ENTITY_NAME = "geoJsonPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoJsonPointService geoJsonPointService;

    public GeoJsonPointResource(GeoJsonPointService geoJsonPointService) {
        this.geoJsonPointService = geoJsonPointService;
    }

    /**
     * {@code POST  /geo-json-points} : Create a new geoJsonPoint.
     *
     * @param geoJsonPointDTO the geoJsonPointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoJsonPointDTO, or with status {@code 400 (Bad Request)} if the geoJsonPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-json-points")
    public ResponseEntity<GeoJsonPointDTO> createGeoJsonPoint(@RequestBody GeoJsonPointDTO geoJsonPointDTO) throws URISyntaxException {
        log.debug("REST request to save GeoJsonPoint : {}", geoJsonPointDTO);
        if (geoJsonPointDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoJsonPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoJsonPointDTO result = geoJsonPointService.save(geoJsonPointDTO);
        return ResponseEntity.created(new URI("/api/geo-json-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-json-points} : Updates an existing geoJsonPoint.
     *
     * @param geoJsonPointDTO the geoJsonPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoJsonPointDTO,
     * or with status {@code 400 (Bad Request)} if the geoJsonPointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoJsonPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-json-points")
    public ResponseEntity<GeoJsonPointDTO> updateGeoJsonPoint(@RequestBody GeoJsonPointDTO geoJsonPointDTO) throws URISyntaxException {
        log.debug("REST request to update GeoJsonPoint : {}", geoJsonPointDTO);
        if (geoJsonPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoJsonPointDTO result = geoJsonPointService.save(geoJsonPointDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoJsonPointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-json-points} : get all the geoJsonPoints.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoJsonPoints in body.
     */
    @GetMapping("/geo-json-points")
    public ResponseEntity<List<GeoJsonPointDTO>> getAllGeoJsonPoints(Pageable pageable) {
        log.debug("REST request to get a page of GeoJsonPoints");
        Page<GeoJsonPointDTO> page = geoJsonPointService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geo-json-points/:id} : get the "id" geoJsonPoint.
     *
     * @param id the id of the geoJsonPointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoJsonPointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-json-points/{id}")
    public ResponseEntity<GeoJsonPointDTO> getGeoJsonPoint(@PathVariable Long id) {
        log.debug("REST request to get GeoJsonPoint : {}", id);
        Optional<GeoJsonPointDTO> geoJsonPointDTO = geoJsonPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoJsonPointDTO);
    }

    /**
     * {@code DELETE  /geo-json-points/:id} : delete the "id" geoJsonPoint.
     *
     * @param id the id of the geoJsonPointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-json-points/{id}")
    public ResponseEntity<Void> deleteGeoJsonPoint(@PathVariable Long id) {
        log.debug("REST request to delete GeoJsonPoint : {}", id);
        geoJsonPointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
