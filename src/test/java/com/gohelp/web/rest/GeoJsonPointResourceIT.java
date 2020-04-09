package com.gohelp.web.rest;

import com.gohelp.GohelpApp;
import com.gohelp.domain.GeoJsonPoint;
import com.gohelp.repository.GeoJsonPointRepository;
import com.gohelp.service.GeoJsonPointService;
import com.gohelp.service.dto.GeoJsonPointDTO;
import com.gohelp.service.mapper.GeoJsonPointMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GeoJsonPointResource} REST controller.
 */
@SpringBootTest(classes = GohelpApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class GeoJsonPointResourceIT {

    private static final Double DEFAULT_X = 1D;
    private static final Double UPDATED_X = 2D;

    private static final Double DEFAULT_Y = 1D;
    private static final Double UPDATED_Y = 2D;

    @Autowired
    private GeoJsonPointRepository geoJsonPointRepository;

    @Autowired
    private GeoJsonPointMapper geoJsonPointMapper;

    @Autowired
    private GeoJsonPointService geoJsonPointService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoJsonPointMockMvc;

    private GeoJsonPoint geoJsonPoint;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoJsonPoint createEntity(EntityManager em) {
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint()
            .x(DEFAULT_X)
            .y(DEFAULT_Y);
        return geoJsonPoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoJsonPoint createUpdatedEntity(EntityManager em) {
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint()
            .x(UPDATED_X)
            .y(UPDATED_Y);
        return geoJsonPoint;
    }

    @BeforeEach
    public void initTest() {
        geoJsonPoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoJsonPoint() throws Exception {
        int databaseSizeBeforeCreate = geoJsonPointRepository.findAll().size();

        // Create the GeoJsonPoint
        GeoJsonPointDTO geoJsonPointDTO = geoJsonPointMapper.toDto(geoJsonPoint);
        restGeoJsonPointMockMvc.perform(post("/api/geo-json-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoJsonPointDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoJsonPoint in the database
        List<GeoJsonPoint> geoJsonPointList = geoJsonPointRepository.findAll();
        assertThat(geoJsonPointList).hasSize(databaseSizeBeforeCreate + 1);
        GeoJsonPoint testGeoJsonPoint = geoJsonPointList.get(geoJsonPointList.size() - 1);
        assertThat(testGeoJsonPoint.getX()).isEqualTo(DEFAULT_X);
        assertThat(testGeoJsonPoint.getY()).isEqualTo(DEFAULT_Y);
    }

    @Test
    @Transactional
    public void createGeoJsonPointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoJsonPointRepository.findAll().size();

        // Create the GeoJsonPoint with an existing ID
        geoJsonPoint.setId(1L);
        GeoJsonPointDTO geoJsonPointDTO = geoJsonPointMapper.toDto(geoJsonPoint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoJsonPointMockMvc.perform(post("/api/geo-json-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoJsonPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoJsonPoint in the database
        List<GeoJsonPoint> geoJsonPointList = geoJsonPointRepository.findAll();
        assertThat(geoJsonPointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeoJsonPoints() throws Exception {
        // Initialize the database
        geoJsonPointRepository.saveAndFlush(geoJsonPoint);

        // Get all the geoJsonPointList
        restGeoJsonPointMockMvc.perform(get("/api/geo-json-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoJsonPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].x").value(hasItem(DEFAULT_X.doubleValue())))
            .andExpect(jsonPath("$.[*].y").value(hasItem(DEFAULT_Y.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getGeoJsonPoint() throws Exception {
        // Initialize the database
        geoJsonPointRepository.saveAndFlush(geoJsonPoint);

        // Get the geoJsonPoint
        restGeoJsonPointMockMvc.perform(get("/api/geo-json-points/{id}", geoJsonPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoJsonPoint.getId().intValue()))
            .andExpect(jsonPath("$.x").value(DEFAULT_X.doubleValue()))
            .andExpect(jsonPath("$.y").value(DEFAULT_Y.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGeoJsonPoint() throws Exception {
        // Get the geoJsonPoint
        restGeoJsonPointMockMvc.perform(get("/api/geo-json-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoJsonPoint() throws Exception {
        // Initialize the database
        geoJsonPointRepository.saveAndFlush(geoJsonPoint);

        int databaseSizeBeforeUpdate = geoJsonPointRepository.findAll().size();

        // Update the geoJsonPoint
        GeoJsonPoint updatedGeoJsonPoint = geoJsonPointRepository.findById(geoJsonPoint.getId()).get();
        // Disconnect from session so that the updates on updatedGeoJsonPoint are not directly saved in db
        em.detach(updatedGeoJsonPoint);
        updatedGeoJsonPoint
            .x(UPDATED_X)
            .y(UPDATED_Y);
        GeoJsonPointDTO geoJsonPointDTO = geoJsonPointMapper.toDto(updatedGeoJsonPoint);

        restGeoJsonPointMockMvc.perform(put("/api/geo-json-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoJsonPointDTO)))
            .andExpect(status().isOk());

        // Validate the GeoJsonPoint in the database
        List<GeoJsonPoint> geoJsonPointList = geoJsonPointRepository.findAll();
        assertThat(geoJsonPointList).hasSize(databaseSizeBeforeUpdate);
        GeoJsonPoint testGeoJsonPoint = geoJsonPointList.get(geoJsonPointList.size() - 1);
        assertThat(testGeoJsonPoint.getX()).isEqualTo(UPDATED_X);
        assertThat(testGeoJsonPoint.getY()).isEqualTo(UPDATED_Y);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoJsonPoint() throws Exception {
        int databaseSizeBeforeUpdate = geoJsonPointRepository.findAll().size();

        // Create the GeoJsonPoint
        GeoJsonPointDTO geoJsonPointDTO = geoJsonPointMapper.toDto(geoJsonPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoJsonPointMockMvc.perform(put("/api/geo-json-points")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoJsonPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoJsonPoint in the database
        List<GeoJsonPoint> geoJsonPointList = geoJsonPointRepository.findAll();
        assertThat(geoJsonPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoJsonPoint() throws Exception {
        // Initialize the database
        geoJsonPointRepository.saveAndFlush(geoJsonPoint);

        int databaseSizeBeforeDelete = geoJsonPointRepository.findAll().size();

        // Delete the geoJsonPoint
        restGeoJsonPointMockMvc.perform(delete("/api/geo-json-points/{id}", geoJsonPoint.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoJsonPoint> geoJsonPointList = geoJsonPointRepository.findAll();
        assertThat(geoJsonPointList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
