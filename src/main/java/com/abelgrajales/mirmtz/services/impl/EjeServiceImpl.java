package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.EjeDTO;
import com.abelgrajales.mirmtz.exception.NotFoundException;
import com.abelgrajales.mirmtz.models.Eje;
import com.abelgrajales.mirmtz.repositories.EjeRepository;
import com.abelgrajales.mirmtz.services.EjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjeServiceImpl implements EjeService {

    private final EjeRepository ejeRepository;

    @Autowired
    public EjeServiceImpl(EjeRepository ejeRepository) {
        this.ejeRepository = ejeRepository;
    }

    @Override
    public EjeDTO save(EjeDTO ejeDTO) {
        Eje eje = Eje.builder()
                .nombre(ejeDTO.getNombre())
                .build();

        return EjeDTO.fromEntity(ejeRepository.save(eje));
    }

    @Override
    public EjeDTO update(EjeDTO ejeDTO) {
        Eje eje = ejeRepository.findById(ejeDTO.getId())
                .orElseThrow(() -> new NotFoundException("Eje con ID " + ejeDTO.getId() + " no encontrado"));

        eje.setNombre(ejeDTO.getNombre());
        return EjeDTO.fromEntity(ejeRepository.save(eje));
    }

    @Override
    public EjeDTO findById(Long id) {
        Eje eje = ejeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Eje con ID " + id + " no encontrado"));
        return EjeDTO.fromEntity(eje);
    }

    @Override
    public void deleteById(Long id) {
        Eje eje = ejeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Eje con ID " + id + " no encontrado"));
        ejeRepository.delete(eje);
    }

    @Override
    public List<EjeDTO> findAll() {
        List<Eje> centroGestores = ejeRepository.findAll();
        return centroGestores.stream().map(EjeDTO::fromEntity).toList();
    }
}
