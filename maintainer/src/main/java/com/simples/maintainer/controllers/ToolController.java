package com.simples.maintainer.controllers;

import com.simples.maintainer.dtos.tool.CreateToolRequest;
import com.simples.maintainer.dtos.tool.UpdateToolRequest;
import com.simples.maintainer.models.services.tool.IToolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tools")
@Tag(name = "tools", description = "Operations related to tools")
public class ToolController {

    private final IToolService toolService;

    public ToolController(IToolService toolService) {
        this.toolService = toolService;
    }

    @Operation(
            summary = "Create a new tool",
            description = "Creates a new tool with the provided request data."
    )
    @PostMapping
    public ResponseEntity<?> createTool(@Valid @RequestBody CreateToolRequest request) {
        return toolService.create(request);
    }

    @Operation(
            summary = "Update an existing tool",
            description = "Updates the information of an existing tool using the provided data."
    )
    @PutMapping
    public ResponseEntity<?> updateTool(@Valid @RequestBody UpdateToolRequest request) {
        return toolService.update(request);
    }

    @Operation(
            summary = "Get all tools",
            description = "Retrieves a list of tools, optionally filtered."
    )
    @GetMapping
    public ResponseEntity<?> findAllTools(@RequestParam Boolean filter) {
        return toolService.findAll(filter);
    }

    @Operation(
            summary = "Get tool by ID",
            description = "Retrieves the details of a specific tool by its unique ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> findToolById(@PathVariable Long id) {
        return toolService.findById(id);
    }

    @Operation(
            summary = "Delete tool by ID",
            description = "Deletes a tool from the system using its unique ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTool(@PathVariable Long id) {
        return toolService.delete(id);
    }

}
