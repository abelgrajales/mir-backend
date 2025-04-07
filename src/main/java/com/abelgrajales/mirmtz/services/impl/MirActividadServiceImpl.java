package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.MirActividadDTO;
import com.abelgrajales.mirmtz.dto.request.MirActividadComponenteRequest;
import com.abelgrajales.mirmtz.models.*;
import com.abelgrajales.mirmtz.repositories.MirActividadRepository;
import com.abelgrajales.mirmtz.repositories.ProgramaRepository;
import com.abelgrajales.mirmtz.services.IndicadorService;
import com.abelgrajales.mirmtz.services.MirActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MirActividadServiceImpl implements MirActividadService {

    private final ProgramaRepository programaRepository;
    private final MirActividadRepository mirActividadRepository;
    private final IndicadorService indicadorService;

    @Autowired
    public MirActividadServiceImpl(ProgramaRepository programaRepository, MirActividadRepository mirActividadRepository, IndicadorService indicadorService) {
        this.programaRepository = programaRepository;
        this.mirActividadRepository = mirActividadRepository;
        this.indicadorService = indicadorService;
    }

    @Override
    public MirActividadDTO save(MirActividadComponenteRequest request) {
        Programa programa = programaRepository.findById(request.getProgramaId())
                .orElseThrow(() -> new RuntimeException("Programa con ID " + request.getProgramaId() + " no encontrado"));

        Indicador indicador = new Indicador();
        indicadorService.actualizarIndicador(indicador, request.getIndicador());

        MirActividad mirActividad = MirActividad.builder()
                .resumenNarrativo(request.getResumenNarrativo())
                .supuestos(request.getSupuestos())
                .programa(programa)
                .indicador(indicador)
                .build();

        indicador.setMirActividad(mirActividad);

        MirActividad savedMirActividad = mirActividadRepository.save(mirActividad);
        return MirActividadDTO.fromEntity(savedMirActividad);
    }

    @Override
    public MirActividadDTO update(MirActividadComponenteRequest request) {
        MirActividad mirActividad = mirActividadRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Actividad con ID " + request.getId() + " no encontrada"));

        mirActividad.setResumenNarrativo(request.getResumenNarrativo());
        mirActividad.setSupuestos(request.getSupuestos());

        Indicador indicador = mirActividad.getIndicador();
        if (indicador == null) {
            indicador = new Indicador();
            mirActividad.setIndicador(indicador);
            indicador.setMirActividad(mirActividad);
        }
        indicadorService.actualizarIndicador(indicador, request.getIndicador());

        MirActividad updatedMirActividad = mirActividadRepository.save(mirActividad);
        return MirActividadDTO.fromEntity(updatedMirActividad);
    }

    @Override
    public MirActividadDTO findById(Long id) {
        MirActividad mirActividad = mirActividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad con ID " + id + " no encontrada"));
        return MirActividadDTO.fromEntity(mirActividad);
    }

    @Override
    public void deleteById(Long id) {
        MirActividad mirActividad = mirActividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad con ID " + id + " no encontrada"));
        mirActividadRepository.delete(mirActividad);
    }

}
