package com.gohelp.repository;

import com.gohelp.domain.RequestType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RequestType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {
}
