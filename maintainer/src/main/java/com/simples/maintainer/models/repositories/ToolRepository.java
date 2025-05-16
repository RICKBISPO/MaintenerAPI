package com.simples.maintainer.models.repositories;

import com.simples.maintainer.models.entities.Tool;
import com.simples.maintainer.projections.ToolFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    @Query("SELECT t.id AS id, t.serialCode AS serialCode FROM Tool t")
    List<ToolFilter> findToolFilter();

}
