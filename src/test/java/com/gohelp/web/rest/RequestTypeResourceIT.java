package com.gohelp.web.rest;

import com.gohelp.GohelpApp;
import com.gohelp.domain.RequestType;
import com.gohelp.repository.RequestTypeRepository;
import com.gohelp.service.RequestTypeService;
import com.gohelp.service.dto.RequestTypeDTO;
import com.gohelp.service.mapper.RequestTypeMapper;

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
 * Integration tests for the {@link RequestTypeResource} REST controller.
 */
@SpringBootTest(classes = GohelpApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class RequestTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private RequestTypeRepository requestTypeRepository;

    @Autowired
    private RequestTypeMapper requestTypeMapper;

    @Autowired
    private RequestTypeService requestTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestTypeMockMvc;

    private RequestType requestType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestType createEntity(EntityManager em) {
        RequestType requestType = new RequestType()
            .name(DEFAULT_NAME);
        return requestType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestType createUpdatedEntity(EntityManager em) {
        RequestType requestType = new RequestType()
            .name(UPDATED_NAME);
        return requestType;
    }

    @BeforeEach
    public void initTest() {
        requestType = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequestType() throws Exception {
        int databaseSizeBeforeCreate = requestTypeRepository.findAll().size();

        // Create the RequestType
        RequestTypeDTO requestTypeDTO = requestTypeMapper.toDto(requestType);
        restRequestTypeMockMvc.perform(post("/api/request-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the RequestType in the database
        List<RequestType> requestTypeList = requestTypeRepository.findAll();
        assertThat(requestTypeList).hasSize(databaseSizeBeforeCreate + 1);
        RequestType testRequestType = requestTypeList.get(requestTypeList.size() - 1);
        assertThat(testRequestType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createRequestTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requestTypeRepository.findAll().size();

        // Create the RequestType with an existing ID
        requestType.setId(1L);
        RequestTypeDTO requestTypeDTO = requestTypeMapper.toDto(requestType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestTypeMockMvc.perform(post("/api/request-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RequestType in the database
        List<RequestType> requestTypeList = requestTypeRepository.findAll();
        assertThat(requestTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRequestTypes() throws Exception {
        // Initialize the database
        requestTypeRepository.saveAndFlush(requestType);

        // Get all the requestTypeList
        restRequestTypeMockMvc.perform(get("/api/request-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requestType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getRequestType() throws Exception {
        // Initialize the database
        requestTypeRepository.saveAndFlush(requestType);

        // Get the requestType
        restRequestTypeMockMvc.perform(get("/api/request-types/{id}", requestType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requestType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingRequestType() throws Exception {
        // Get the requestType
        restRequestTypeMockMvc.perform(get("/api/request-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequestType() throws Exception {
        // Initialize the database
        requestTypeRepository.saveAndFlush(requestType);

        int databaseSizeBeforeUpdate = requestTypeRepository.findAll().size();

        // Update the requestType
        RequestType updatedRequestType = requestTypeRepository.findById(requestType.getId()).get();
        // Disconnect from session so that the updates on updatedRequestType are not directly saved in db
        em.detach(updatedRequestType);
        updatedRequestType
            .name(UPDATED_NAME);
        RequestTypeDTO requestTypeDTO = requestTypeMapper.toDto(updatedRequestType);

        restRequestTypeMockMvc.perform(put("/api/request-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestTypeDTO)))
            .andExpect(status().isOk());

        // Validate the RequestType in the database
        List<RequestType> requestTypeList = requestTypeRepository.findAll();
        assertThat(requestTypeList).hasSize(databaseSizeBeforeUpdate);
        RequestType testRequestType = requestTypeList.get(requestTypeList.size() - 1);
        assertThat(testRequestType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingRequestType() throws Exception {
        int databaseSizeBeforeUpdate = requestTypeRepository.findAll().size();

        // Create the RequestType
        RequestTypeDTO requestTypeDTO = requestTypeMapper.toDto(requestType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestTypeMockMvc.perform(put("/api/request-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RequestType in the database
        List<RequestType> requestTypeList = requestTypeRepository.findAll();
        assertThat(requestTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequestType() throws Exception {
        // Initialize the database
        requestTypeRepository.saveAndFlush(requestType);

        int databaseSizeBeforeDelete = requestTypeRepository.findAll().size();

        // Delete the requestType
        restRequestTypeMockMvc.perform(delete("/api/request-types/{id}", requestType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RequestType> requestTypeList = requestTypeRepository.findAll();
        assertThat(requestTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
