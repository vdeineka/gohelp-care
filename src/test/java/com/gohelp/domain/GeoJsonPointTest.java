package com.gohelp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gohelp.web.rest.TestUtil;

public class GeoJsonPointTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoJsonPoint.class);
        GeoJsonPoint geoJsonPoint1 = new GeoJsonPoint();
        geoJsonPoint1.setId(1L);
        GeoJsonPoint geoJsonPoint2 = new GeoJsonPoint();
        geoJsonPoint2.setId(geoJsonPoint1.getId());
        assertThat(geoJsonPoint1).isEqualTo(geoJsonPoint2);
        geoJsonPoint2.setId(2L);
        assertThat(geoJsonPoint1).isNotEqualTo(geoJsonPoint2);
        geoJsonPoint1.setId(null);
        assertThat(geoJsonPoint1).isNotEqualTo(geoJsonPoint2);
    }
}
