package com.gohelp.service.mapper;


import com.gohelp.domain.*;
import com.gohelp.service.dto.RequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Request} and its DTO {@link RequestDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, GeoJsonPointMapper.class, RequestTypeMapper.class})
public interface RequestMapper extends EntityMapper<RequestDTO, Request> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "type.id", target = "typeId")
    RequestDTO toDto(Request request);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "typeId", target = "type")
    Request toEntity(RequestDTO requestDTO);

    default Request fromId(Long id) {
        if (id == null) {
            return null;
        }
        Request request = new Request();
        request.setId(id);
        return request;
    }
}
