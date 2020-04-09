package com.gohelp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gohelp.web.rest.TestUtil;

public class RequestTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestTypeDTO.class);
        RequestTypeDTO requestTypeDTO1 = new RequestTypeDTO();
        requestTypeDTO1.setId(1L);
        RequestTypeDTO requestTypeDTO2 = new RequestTypeDTO();
        assertThat(requestTypeDTO1).isNotEqualTo(requestTypeDTO2);
        requestTypeDTO2.setId(requestTypeDTO1.getId());
        assertThat(requestTypeDTO1).isEqualTo(requestTypeDTO2);
        requestTypeDTO2.setId(2L);
        assertThat(requestTypeDTO1).isNotEqualTo(requestTypeDTO2);
        requestTypeDTO1.setId(null);
        assertThat(requestTypeDTO1).isNotEqualTo(requestTypeDTO2);
    }
}
