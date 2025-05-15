package com.simples.maintainer.models.services.tool;

import com.simples.maintainer.dtos.tool.CreateToolRequest;
import com.simples.maintainer.dtos.tool.UpdateToolRequest;
import org.springframework.http.ResponseEntity;

public interface IToolService {

    ResponseEntity<?> create(CreateToolRequest request);
    ResponseEntity<?> update(UpdateToolRequest request);
    ResponseEntity<?> findAll();
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> delete(Long id);

}
