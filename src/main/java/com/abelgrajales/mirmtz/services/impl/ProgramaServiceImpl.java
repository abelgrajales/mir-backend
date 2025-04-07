package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.ProgramaDTO;
import com.abelgrajales.mirmtz.dto.request.ProgramaRequest;
import com.abelgrajales.mirmtz.exception.NotFoundException;
import com.abelgrajales.mirmtz.models.*;
import com.abelgrajales.mirmtz.repositories.*;
import com.abelgrajales.mirmtz.services.ProgramaService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramaServiceImpl implements ProgramaService {

    private final ProgramaRepository programaRepository;
    private final EjeRepository ejeRepository;
    private final SubtemaRepository subtemaRepository;
    private final EstrategiaRepository estrategiaRepository;
    private final CentroGestorRepository centroGestorRepository;

    @Autowired
    public ProgramaServiceImpl(ProgramaRepository programaRepository, EjeRepository ejeRepository, SubtemaRepository subtemaRepository, EstrategiaRepository estrategiaRepository, CentroGestorRepository centroGestorRepository) {
        this.programaRepository = programaRepository;
        this.ejeRepository = ejeRepository;
        this.subtemaRepository = subtemaRepository;
        this.estrategiaRepository = estrategiaRepository;
        this.centroGestorRepository = centroGestorRepository;
    }

    @Override
    public ProgramaDTO save(ProgramaRequest request) {
        EntidadesPrograma entidades = getEntidadesPrograma(request);

        Programa programa = Programa.builder()
                .nombre(request.getNombre())
                .eje(entidades.getEje())
                .subtema(entidades.getSubtema())
                .estrategia(entidades.getEstrategia())
                .centroGestor(entidades.getCentroGestor())
                .build();

        return ProgramaDTO.fromEntity(programaRepository.save(programa));
    }

    @Override
    public ProgramaDTO update(ProgramaRequest request) {
        Programa programa = programaRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Programa con ID " + request.getId() + " no encontrado"));

        EntidadesPrograma entidades = getEntidadesPrograma(request);

        programa.setNombre(request.getNombre());
        programa.setEje(entidades.getEje());
        programa.setSubtema(entidades.getSubtema());
        programa.setEstrategia(entidades.getEstrategia());
        programa.setCentroGestor(entidades.getCentroGestor());

        return ProgramaDTO.fromEntity(programaRepository.save(programa));
    }

    @Override
    public void deleteById(Long id) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Programa con ID " + id + " no encontrado"));
        programaRepository.delete(programa);
    }

    @Override
    public ProgramaDTO findById(Long id) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Programa con ID " + id + " no encontrado"));
        return ProgramaDTO.fromEntityAll(programa);
    }

    @Override
    public List<ProgramaDTO> findAll() {
        List<Programa> programas = programaRepository.findAll();
        return programas.stream().map(ProgramaDTO::fromEntity).toList();
    }


    private EntidadesPrograma getEntidadesPrograma(ProgramaRequest request) {
        EntidadesPrograma entidades = new EntidadesPrograma();

        entidades.setEje(ejeRepository.findById(request.getEjeId())
                .orElseThrow(() -> new NotFoundException("Eje con ID " + request.getEjeId() + " no encontrado")));

        entidades.setSubtema(subtemaRepository.findById(request.getSubtemaId())
                .orElseThrow(() -> new NotFoundException("Subtema con ID " + request.getSubtemaId() + " no encontrado")));

        entidades.setEstrategia(estrategiaRepository.findById(request.getEstrategiaId())
                .orElseThrow(() -> new NotFoundException("Estrategia con ID " + request.getEstrategiaId() + " no encontrado")));

        entidades.setCentroGestor(centroGestorRepository.findById(request.getCentroGestorId())
                .orElseThrow(() -> new NotFoundException("Centro Gestor con ID " + request.getCentroGestorId() + " no encontrado")));

        return entidades;
    }

    @Getter
    @Setter
    private static class EntidadesPrograma {
        Eje eje;
        Subtema subtema;
        Estrategia estrategia;
        CentroGestor centroGestor;
    }

}
