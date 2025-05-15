package com.simples.maintainer.models.services.maintenance.tool;

import com.simples.maintainer.dtos.maintenance.tool.CreateMaintenanceToolRequest;
import com.simples.maintainer.dtos.maintenance.tool.UpdateMaintenanceToolRequest;
import com.simples.maintainer.exceptions.notfound.MaintenanceNotFoundException;
import com.simples.maintainer.exceptions.notfound.MaintenanceToolNotFoundException;
import com.simples.maintainer.exceptions.notfound.ToolNotFoundException;
import com.simples.maintainer.models.entities.MaintenanceTool;
import com.simples.maintainer.models.entities.MaintenanceToolId;
import com.simples.maintainer.models.repositories.MaintenanceRepository;
import com.simples.maintainer.models.repositories.MaintenanceToolRepository;
import com.simples.maintainer.models.repositories.ToolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceToolService implements IMaintenanceToolService {

    private final MaintenanceToolRepository maintenanceToolRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final ToolRepository toolRepository;

    public MaintenanceToolService(
            MaintenanceToolRepository maintenanceToolRepository,
            MaintenanceRepository maintenanceRepository,
            ToolRepository toolRepository
    ) {
        this.maintenanceToolRepository = maintenanceToolRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.toolRepository = toolRepository;
    }

    @Override
    public ResponseEntity<?> create(CreateMaintenanceToolRequest request) {
        var maintenance = maintenanceRepository.findById(request.maintenanceId())
                .orElseThrow(MaintenanceNotFoundException::new);

        var tool = toolRepository.findById(request.toolId())
                .orElseThrow(ToolNotFoundException::new);

        var entity = new MaintenanceTool();
        entity.setQuantityUsed(request.quantityUsed());
        entity.setMaintenance(maintenance);
        entity.setTool(tool);

        var response = maintenanceToolRepository.save(entity);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(UpdateMaintenanceToolRequest request) {
        var entity = maintenanceToolRepository
                .findById(new MaintenanceToolId(request.maintenanceId(), request.toolId()))
                .orElseThrow(MaintenanceToolNotFoundException::new);

        request.quantityUsed().ifPresent(entity::setQuantityUsed);

        var response = maintenanceToolRepository.save(entity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAll(Long maintenanceId, Long toolId) {

        var response = maintenanceToolRepository.findAll();

        if (maintenanceId != null && toolId == null) {
            response = maintenanceToolRepository.findAllByMaintenanceId(maintenanceId)
                    .orElseThrow(MaintenanceToolNotFoundException::new);
        }

        if (toolId != null && maintenanceId == null) {
            response = maintenanceToolRepository.findAllByToolId(toolId)
                    .orElseThrow(MaintenanceToolNotFoundException::new);
        }

        if (response.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long maintenanceId, Long toolId) {
        var response = maintenanceToolRepository
                .findById(new MaintenanceToolId(maintenanceId, toolId))
                .orElseThrow(MaintenanceToolNotFoundException::new);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long maintenanceId, Long toolId) {
        var entity = maintenanceToolRepository
                .findById(new MaintenanceToolId(maintenanceId, toolId))
                .orElseThrow(MaintenanceToolNotFoundException::new);

        maintenanceToolRepository.delete(entity);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
