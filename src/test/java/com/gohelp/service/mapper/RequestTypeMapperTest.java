package com.gohelp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestTypeMapperTest {

    private RequestTypeMapper requestTypeMapper;

    @BeforeEach
    public void setUp() {
        requestTypeMapper = new RequestTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(requestTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(requestTypeMapper.fromId(null)).isNull();
    }
}
