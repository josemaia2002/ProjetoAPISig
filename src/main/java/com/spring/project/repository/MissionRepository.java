package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.model.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

}
