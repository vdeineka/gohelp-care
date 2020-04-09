package com.gohelp.service.mapper;


import com.gohelp.domain.*;
import com.gohelp.service.dto.GeoJsonPointDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoJsonPoint} and its DTO {@link GeoJsonPointDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeoJsonPointMapper extends EntityMapper<GeoJsonPointDTO, GeoJsonPoint> {



    default GeoJsonPoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint();
        geoJsonPoint.setId(id);
        return geoJsonPoint;
    }
}
