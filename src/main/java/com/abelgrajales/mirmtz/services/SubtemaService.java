package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.SubtemaDTO;

import java.util.List;

public interface SubtemaService {

    SubtemaDTO save(SubtemaDTO subtemaDTO);

    SubtemaDTO update(SubtemaDTO subtemaDTO);

    SubtemaDTO findById(Long id);

    void deleteById(Long id);

    List<SubtemaDTO> findAll();
}
