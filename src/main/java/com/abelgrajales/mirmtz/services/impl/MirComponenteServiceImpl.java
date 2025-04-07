package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.MirComponenteDTO;
import com.abelgrajales.mirmtz.dto.request.MirActividadComponenteRequest;
import com.abelgrajales.mirmtz.models.*;
import com.abelgrajales.mirmtz.repositories.MirComponenteRepository;
import com.abelgrajales.mirmtz.repositories.ProgramaRepository;
import com.abelgrajales.mirmtz.services.IndicadorService;
import com.abelgrajales.mirmtz.services.MirComponenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MirComponenteServiceImpl implements MirComponenteService {

    private final MirComponenteRepository mirComponenteRepository;
    private final ProgramaRepository programaRepository;
    private final IndicadorService indicadorService;

    @Autowired
    public MirComponenteServiceImpl(MirComponenteRepository mirComponenteRepository, ProgramaRepository programaRepository, IndicadorService indicadorService) {
        this.mirComponenteRepository = mirComponenteRepository;
        this.programaRepository = programaRepository;
        this.indicadorService = indicadorService;
    }

    @Override
    public MirComponenteDTO save(MirActividadComponenteRequest request) {
        Programa programa = programaRepository.findById(request.getProgramaId())
                .orElseThrow(() -> new RuntimeException("Programa con ID " + request.getProgramaId() + " no encontrado"));

        Indicador indicador = new Indicador();
        indicadorService.actualizarIndicador(indicador, request.getIndicador());

        MirComponente mirActividad = MirComponente.builder()
                .resumenNarrativo(request.getResumenNarrativo())
                .supuestos(request.getSupuestos())
                .programa(programa)
                .indicador(indicador)
                .build();

        indicador.setMirComponente(mirActividad);

        MirComponente savedMirComponente = mirComponenteRepository.save(mirActividad);
        return MirComponenteDTO.fromEntity(savedMirComponente);
    }

    @Override
    public MirComponenteDTO update(MirActividadComponenteRequest request) {
        MirComponente mirComponente = mirComponenteRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Actividad con ID " + request.getId() + " no encontrada"));

        mirComponente.setResumenNarrativo(request.getResumenNarrativo());
        mirComponente.setSupuestos(request.getSupuestos());

        Indicador indicador = mirComponente.getIndicador();
        if (indicador == null) {
            indicador = new Indicador();
            mirComponente.setIndicador(indicador);
            indicador.setMirComponente(mirComponente);
        }
        indicadorService.actualizarIndicador(indicador, request.getIndicador());

        MirComponente updatedMirActividad = mirComponenteRepository.save(mirComponente);
        return MirComponenteDTO.fromEntity(updatedMirActividad);
    }

    @Override
    public MirComponenteDTO findById(Long id) {
        MirComponente mirComponente = mirComponenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad con ID " + id + " no encontrada"));
        return MirComponenteDTO.fromEntity(mirComponente);
    }

    @Override
    public void deleteById(Long id) {
        MirComponente mirComponente = mirComponenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad con ID " + id + " no encontrada"));
        mirComponenteRepository.delete(mirComponente);
    }

}
