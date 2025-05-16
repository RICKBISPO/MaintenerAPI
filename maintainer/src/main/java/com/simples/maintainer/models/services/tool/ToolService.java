package com.simples.maintainer.models.services.tool;

import com.simples.maintainer.dtos.tool.CreateToolRequest;
import com.simples.maintainer.dtos.tool.UpdateToolRequest;
import com.simples.maintainer.exceptions.AlreadyExistsException;
import com.simples.maintainer.exceptions.notfound.ToolNotFoundException;
import com.simples.maintainer.models.entities.Tool;
import com.simples.maintainer.models.repositories.ToolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToolService implements IToolService {

    private final ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Override
    public ResponseEntity<?> create(CreateToolRequest request) {
        var entity = new Tool();
        entity.setName(request.name());
        entity.setSerialCode(request.serialCode());
        entity.setPurchaseDate(request.purchaseDate());

        validateTool(entity);

        var response = toolRepository.save(entity);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(UpdateToolRequest request) {
        var entity = toolRepository.findById(request.id())
                .orElseThrow(ToolNotFoundException::new);

        Optional.ofNullable(request.name()).ifPresent(entity::setName);
        Optional.ofNullable(request.serialCode()).ifPresent(entity::setSerialCode);
        Optional.ofNullable(request.purchaseDate()).ifPresent(entity::setPurchaseDate);

        validateTool(entity);

        var response = toolRepository.save(entity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAll(Boolean filter) {
        var response = toolRepository.findAll();

        if (response.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        if (filter) return new ResponseEntity<>(toolRepository.findToolFilter(), HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        var response = toolRepository.findById(id)
                .orElseThrow(ToolNotFoundException::new);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        var entity = toolRepository.findById(id)
                .orElseThrow(ToolNotFoundException::new);

        toolRepository.delete(entity);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public void validateTool(Tool tool) {
        var response = toolRepository.findAll();
        response.forEach(t -> {
                if (!t.getId().equals(tool.getId()) && t.getSerialCode().equals(tool.getSerialCode())) {
                    throw new AlreadyExistsException("serial code already exists");
                }
            }
        );
    }

}
