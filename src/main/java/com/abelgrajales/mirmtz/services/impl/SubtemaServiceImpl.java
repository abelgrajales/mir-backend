package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.SubtemaDTO;
import com.abelgrajales.mirmtz.exception.NotFoundException;
import com.abelgrajales.mirmtz.models.Subtema;
import com.abelgrajales.mirmtz.repositories.SubtemaRepository;
import com.abelgrajales.mirmtz.services.SubtemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtemaServiceImpl implements SubtemaService {

    private final SubtemaRepository subtemaRepository;

    @Autowired
    public SubtemaServiceImpl(SubtemaRepository subtemaRepository) {
        this.subtemaRepository = subtemaRepository;
    }

    @Override
    public SubtemaDTO save(SubtemaDTO subtemaDTO) {
        Subtema subtema = Subtema.builder()
                .nombre(subtemaDTO.getNombre())
                .build();

        return SubtemaDTO.fromEntity(subtemaRepository.save(subtema));
    }

    @Override
    public SubtemaDTO update(SubtemaDTO subtemaDTO) {
        Subtema subtema = subtemaRepository.findById(subtemaDTO.getId())
                .orElseThrow(() -> new NotFoundException("Subtema con ID " + subtemaDTO.getId() + " no encontrado"));

        subtema.setNombre(subtemaDTO.getNombre());
        return SubtemaDTO.fromEntity(subtemaRepository.save(subtema));
    }

    @Override
    public SubtemaDTO findById(Long id) {
        Subtema subtema = subtemaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subtema con ID " + id + " no encontrado"));
        return SubtemaDTO.fromEntity(subtema);
    }

    @Override
    public void deleteById(Long id) {
        Subtema subtema = subtemaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subtema con ID " + id + " no encontrado"));
        subtemaRepository.delete(subtema);
    }

    @Override
    public List<SubtemaDTO> findAll() {
        List<Subtema> subtemas = subtemaRepository.findAll();
        return subtemas.stream().map(SubtemaDTO::fromEntity).toList();
    }
}
