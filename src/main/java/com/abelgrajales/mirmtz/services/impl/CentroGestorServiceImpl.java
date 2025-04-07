package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.CentroGestorDTO;
import com.abelgrajales.mirmtz.exception.NotFoundException;
import com.abelgrajales.mirmtz.models.CentroGestor;
import com.abelgrajales.mirmtz.repositories.CentroGestorRepository;
import com.abelgrajales.mirmtz.services.CentroGestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroGestorServiceImpl implements CentroGestorService {

    private final CentroGestorRepository centroGestorRepository;

    @Autowired
    public CentroGestorServiceImpl(CentroGestorRepository centroGestorRepository) {
        this.centroGestorRepository = centroGestorRepository;
    }

    @Override
    public CentroGestorDTO save(CentroGestorDTO centroGestorDTO) {
        CentroGestor centroGestor = CentroGestor.builder()
                .nombre(centroGestorDTO.getNombre())
                .build();

        return CentroGestorDTO.fromEntity(centroGestorRepository.save(centroGestor));
    }

    @Override
    public CentroGestorDTO update(CentroGestorDTO centroGestorDTO) {
        CentroGestor centroGestor = centroGestorRepository.findById(centroGestorDTO.getId())
                .orElseThrow(() -> new NotFoundException("Centro Gestor con ID " + centroGestorDTO.getId() + " no encontrado"));

        centroGestor.setNombre(centroGestorDTO.getNombre());
        return CentroGestorDTO.fromEntity(centroGestorRepository.save(centroGestor));
    }

    @Override
    public CentroGestorDTO findById(Long id) {
        CentroGestor centroGestor = centroGestorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Centro Gestor con ID " + id + " no encontrado"));
        return CentroGestorDTO.fromEntity(centroGestor);
    }

    @Override
    public void deleteById(Long id) {
        CentroGestor centroGestor = centroGestorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Centro Gestor con ID " + id + " no encontrado"));
        centroGestorRepository.delete(centroGestor);
    }

    @Override
    public List<CentroGestorDTO> findAll() {
        List<CentroGestor> centroGestores = centroGestorRepository.findAll();
        return centroGestores.stream().map(CentroGestorDTO::fromEntity).toList();
    }
}
