package com.workshop.bouali.repositories;

import com.workshop.bouali.models.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByEmployees_Id(Integer employeeId);
}
