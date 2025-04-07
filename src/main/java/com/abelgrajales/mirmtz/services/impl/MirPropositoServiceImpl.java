package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.MirPropositoDTO;
import com.abelgrajales.mirmtz.dto.request.MirFinPropositoRequest;
import com.abelgrajales.mirmtz.models.MirProposito;
import com.abelgrajales.mirmtz.models.Programa;
import com.abelgrajales.mirmtz.repositories.MirPropositoRepository;
import com.abelgrajales.mirmtz.repositories.ProgramaRepository;
import com.abelgrajales.mirmtz.services.MirPropositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MirPropositoServiceImpl implements MirPropositoService {

    private final MirPropositoRepository mirPropositoRepository;
    private final ProgramaRepository programaRepository;

    @Autowired
    public MirPropositoServiceImpl(MirPropositoRepository mirPropositoRepository, ProgramaRepository programaRepository) {
        this.mirPropositoRepository = mirPropositoRepository;
        this.programaRepository = programaRepository;
    }

    @Override
    public MirPropositoDTO save(MirFinPropositoRequest request) {
        Programa programa = programaRepository.findById(request.getProgramaId())
                .orElseThrow(() -> new RuntimeException("Programa con ID " + request.getProgramaId() + " no encontrado"));

        MirProposito mirProposito = MirProposito.builder()
                .resumenNarrativo(request.getResumenNarrativo())
                .supuestos(request.getSupuestos())
                .programa(programa)
                .build();

        return MirPropositoDTO.fromEntity(mirPropositoRepository.save(mirProposito));
    }

    @Override
    public MirPropositoDTO update(MirFinPropositoRequest request) {
        MirProposito mirProposito = mirPropositoRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Nivel Proposito con ID " + request.getId() + " no encontrado"));

        mirProposito.setResumenNarrativo(request.getResumenNarrativo());
        mirProposito.setSupuestos(request.getSupuestos());
        return MirPropositoDTO.fromEntity(mirPropositoRepository.save(mirProposito));
    }

    @Override
    public MirPropositoDTO findById(Long id) {
        MirProposito mirProposito = mirPropositoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nivel Proposito con ID " + id + " no encontrado"));
        return MirPropositoDTO.fromEntity(mirProposito);
    }
}
