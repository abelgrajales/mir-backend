package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.IndicadorDTO;
import com.abelgrajales.mirmtz.dto.request.IndicadorRequest;
import com.abelgrajales.mirmtz.services.IndicadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indicador")
public class IndicadorController {

    private final IndicadorService indicadorService;

    @Autowired
    public IndicadorController(IndicadorService indicadorService) {
        this.indicadorService = indicadorService;
    }

    @PostMapping
    public ResponseEntity<IndicadorDTO> save(@Valid @RequestBody IndicadorRequest request) {
        IndicadorDTO savedIndicador = indicadorService.save(request);
        return ResponseEntity.ok(savedIndicador);
    }

    @PutMapping
    public ResponseEntity<IndicadorDTO> update(@Valid @RequestBody IndicadorRequest request) {
        IndicadorDTO updatedIndicador = indicadorService.update(request);
        return ResponseEntity.ok(updatedIndicador);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndicadorDTO> findById(@PathVariable Long id) {
        IndicadorDTO indicadorDTO = indicadorService.findById(id);
        return ResponseEntity.ok(indicadorDTO);
    }

    @GetMapping("/mirfin/{id}")
    public ResponseEntity<List<IndicadorDTO>> findByMirActividadId(@PathVariable Long id) {
        List<IndicadorDTO> indicadorDTO = indicadorService.findByMirFin(id);
        return ResponseEntity.ok(indicadorDTO);
    }

    @GetMapping("/mirproposito/{id}")
    public ResponseEntity<List<IndicadorDTO>> findByMirPropositoId(@PathVariable Long id) {
        List<IndicadorDTO> indicadorDTO = indicadorService.findByMirProposito(id);
        return ResponseEntity.ok(indicadorDTO);
    }

}
