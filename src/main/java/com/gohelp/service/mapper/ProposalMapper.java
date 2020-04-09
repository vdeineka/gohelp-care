package com.gohelp.service.mapper;


import com.gohelp.domain.*;
import com.gohelp.service.dto.ProposalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Proposal} and its DTO {@link ProposalDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, RequestMapper.class})
public interface ProposalMapper extends EntityMapper<ProposalDTO, Proposal> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "request.id", target = "requestId")
    ProposalDTO toDto(Proposal proposal);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "requestId", target = "request")
    Proposal toEntity(ProposalDTO proposalDTO);

    default Proposal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Proposal proposal = new Proposal();
        proposal.setId(id);
        return proposal;
    }
}
