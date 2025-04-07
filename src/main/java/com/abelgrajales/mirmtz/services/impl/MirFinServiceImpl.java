package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.MirFinDTO;
import com.abelgrajales.mirmtz.dto.request.MirFinPropositoRequest;
import com.abelgrajales.mirmtz.models.MirFin;
import com.abelgrajales.mirmtz.models.Programa;
import com.abelgrajales.mirmtz.repositories.MirFinRepository;
import com.abelgrajales.mirmtz.repositories.ProgramaRepository;
import com.abelgrajales.mirmtz.services.MirFinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MirFinServiceImpl implements MirFinService {

    private final MirFinRepository mirFinRepository;
    private final ProgramaRepository programaRepository;

    @Autowired
    public MirFinServiceImpl(MirFinRepository mirFinRepository, ProgramaRepository programaRepository) {
        this.mirFinRepository = mirFinRepository;
        this.programaRepository = programaRepository;
    }

    @Override
    public MirFinDTO save(MirFinPropositoRequest request) {
        Programa programa = programaRepository.findById(request.getProgramaId())
                .orElseThrow(() -> new RuntimeException("Programa con ID " + request.getProgramaId() + " no encontrado"));

        MirFin mirFin = MirFin.builder()
                .resumenNarrativo(request.getResumenNarrativo())
                .supuestos(request.getSupuestos())
                .programa(programa)
                .build();

        return MirFinDTO.fromEntity(mirFinRepository.save(mirFin));
    }

    @Override
    public MirFinDTO update(MirFinPropositoRequest request) {
        MirFin mirFin = mirFinRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("MirFin con ID " + request.getId() + " no encontrado"));

        mirFin.setResumenNarrativo(request.getResumenNarrativo());
        mirFin.setSupuestos(request.getSupuestos());
        return MirFinDTO.fromEntity(mirFinRepository.save(mirFin));
    }

    @Override
    public MirFinDTO findById(Long id) {
        MirFin mirFin = mirFinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nivel Fin con ID " + id + " no encontrado"));
        return MirFinDTO.fromEntity(mirFin);
    }
}
