package com.zzzdream.springreserve.repository;

import com.zzzdream.springreserve.model.auth.User;
import com.zzzdream.springreserve.model.services.Servicee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ServiceRepository  extends JpaRepository<Servicee, Integer> {
    Optional<Servicee> findByName(String name);
    boolean existsByName(String name);
}
