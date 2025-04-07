package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.EjeDTO;

import java.util.List;

public interface EjeService {

    EjeDTO save(EjeDTO ejeDTO);

    EjeDTO update(EjeDTO ejeDTO);

    EjeDTO findById(Long id);

    void deleteById(Long id);

    List<EjeDTO> findAll();
}
