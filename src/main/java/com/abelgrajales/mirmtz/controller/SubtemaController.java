package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.SubtemaDTO;
import com.abelgrajales.mirmtz.services.SubtemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtema")
public class SubtemaController {

    private final SubtemaService subtemaService;

    @Autowired
    public SubtemaController(SubtemaService subtemaService) {
        this.subtemaService = subtemaService;
    }

    @PostMapping
    public ResponseEntity<SubtemaDTO> save(@Valid @RequestBody SubtemaDTO subtemaDTO) {
        SubtemaDTO savedSubtema = subtemaService.save(subtemaDTO);
        return ResponseEntity.ok(savedSubtema);
    }

    @PutMapping
    public ResponseEntity<SubtemaDTO> update(@Valid @RequestBody SubtemaDTO subtemaDTO) {
        SubtemaDTO updatedSubtema = subtemaService.update(subtemaDTO);
        return ResponseEntity.ok(updatedSubtema);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubtemaDTO> findById(@PathVariable Long id) {
        SubtemaDTO subtemaDTO = subtemaService.findById(id);
        return ResponseEntity.ok(subtemaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        subtemaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SubtemaDTO>> findAll() {
        List<SubtemaDTO> subtemas = subtemaService.findAll();
        return ResponseEntity.ok(subtemas);
    }
}
