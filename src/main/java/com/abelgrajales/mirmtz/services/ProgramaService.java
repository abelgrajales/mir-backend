package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.ProgramaDTO;
import com.abelgrajales.mirmtz.dto.request.ProgramaRequest;

import java.util.List;

public interface ProgramaService {

    ProgramaDTO save(ProgramaRequest request);

    ProgramaDTO update(ProgramaRequest request);

    void deleteById(Long id);

    ProgramaDTO findById(Long id);

    List<ProgramaDTO> findAll();
}
