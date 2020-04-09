package com.gohelp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoJsonPointMapperTest {

    private GeoJsonPointMapper geoJsonPointMapper;

    @BeforeEach
    public void setUp() {
        geoJsonPointMapper = new GeoJsonPointMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geoJsonPointMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoJsonPointMapper.fromId(null)).isNull();
    }
}
