package com.gohelp.service.mapper;


import com.gohelp.domain.*;
import com.gohelp.service.dto.RequestTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RequestType} and its DTO {@link RequestTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RequestTypeMapper extends EntityMapper<RequestTypeDTO, RequestType> {



    default RequestType fromId(Long id) {
        if (id == null) {
            return null;
        }
        RequestType requestType = new RequestType();
        requestType.setId(id);
        return requestType;
    }
}
