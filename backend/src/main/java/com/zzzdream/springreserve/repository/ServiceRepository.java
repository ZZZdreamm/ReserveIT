package com.zzzdream.springreserve.repository;

import com.zzzdream.springreserve.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceRepository  extends JpaRepository<Service, Integer> {

}
