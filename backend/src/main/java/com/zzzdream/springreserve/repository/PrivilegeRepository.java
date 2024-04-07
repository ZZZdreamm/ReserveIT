package com.zzzdream.springreserve.repository;

import com.zzzdream.springreserve.model.auth.Privilege;
import com.zzzdream.springreserve.model.auth.PrivilegeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    Privilege findByPrivilegeType(PrivilegeType privilegeType);
}
