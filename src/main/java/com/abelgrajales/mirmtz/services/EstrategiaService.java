package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.EstrategiaDTO;

import java.util.List;

public interface EstrategiaService {

    EstrategiaDTO save(EstrategiaDTO estrategiaDTO);

    EstrategiaDTO update(EstrategiaDTO estrategiaDTO);

    EstrategiaDTO findById(Long id);

    void deleteById(Long id);

    List<EstrategiaDTO> findAll();
}
