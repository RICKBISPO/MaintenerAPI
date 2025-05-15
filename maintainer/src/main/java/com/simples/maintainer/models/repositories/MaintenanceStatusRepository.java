package com.simples.maintainer.models.repositories;

import com.simples.maintainer.models.entities.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceStatusRepository extends JpaRepository<MaintenanceStatus, Long> { }
