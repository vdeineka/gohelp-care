package com.gohelp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gohelp.web.rest.TestUtil;

public class GeoJsonPointDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoJsonPointDTO.class);
        GeoJsonPointDTO geoJsonPointDTO1 = new GeoJsonPointDTO();
        geoJsonPointDTO1.setId(1L);
        GeoJsonPointDTO geoJsonPointDTO2 = new GeoJsonPointDTO();
        assertThat(geoJsonPointDTO1).isNotEqualTo(geoJsonPointDTO2);
        geoJsonPointDTO2.setId(geoJsonPointDTO1.getId());
        assertThat(geoJsonPointDTO1).isEqualTo(geoJsonPointDTO2);
        geoJsonPointDTO2.setId(2L);
        assertThat(geoJsonPointDTO1).isNotEqualTo(geoJsonPointDTO2);
        geoJsonPointDTO1.setId(null);
        assertThat(geoJsonPointDTO1).isNotEqualTo(geoJsonPointDTO2);
    }
}
