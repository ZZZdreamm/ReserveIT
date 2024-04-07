package com.zzzdream.springreserve.repository;

import com.zzzdream.springreserve.model.auth.Role;
import com.zzzdream.springreserve.model.auth.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {
        Role findByRoleType(RoleType roleType);
}