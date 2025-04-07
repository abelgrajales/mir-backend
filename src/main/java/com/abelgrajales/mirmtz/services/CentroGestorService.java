package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.CentroGestorDTO;

import java.util.List;

public interface CentroGestorService {

    CentroGestorDTO save(CentroGestorDTO centroGestorDTO);

    CentroGestorDTO update(CentroGestorDTO centroGestorDTO);

    CentroGestorDTO findById(Long id);

    void deleteById(Long id);

    List<CentroGestorDTO> findAll();
}
