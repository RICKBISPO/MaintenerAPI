package com.simples.maintainer.models.repositories;

import com.simples.maintainer.models.entities.MaintenanceTool;
import com.simples.maintainer.models.entities.MaintenanceToolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaintenanceToolRepository extends JpaRepository<MaintenanceTool, MaintenanceToolId> {

    @Query(value = "SELECT * FROM maintenance_tool WHERE maintenance_id = :id", nativeQuery = true)
    Optional<List<MaintenanceTool>> findAllByMaintenanceId(@Param("id") Long id);

    @Query("SELECT mt FROM MaintenanceTool mt WHERE mt.tool.id = :id")
    Optional<List<MaintenanceTool>> findAllByToolId(@Param("id") Long id);

}
