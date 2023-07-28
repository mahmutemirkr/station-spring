package com.mekstart.repository;

import com.mekstart.domain.Bus;
import com.mekstart.dto.response.BusResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {



}
