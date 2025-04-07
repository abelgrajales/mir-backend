package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.EstrategiaDTO;
import com.abelgrajales.mirmtz.exception.NotFoundException;
import com.abelgrajales.mirmtz.models.Estrategia;
import com.abelgrajales.mirmtz.repositories.EstrategiaRepository;
import com.abelgrajales.mirmtz.services.EstrategiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstrategiaServiceImpl implements EstrategiaService {

    private final EstrategiaRepository estrategiaRepository;

    @Autowired
    public EstrategiaServiceImpl(EstrategiaRepository estrategiaRepository) {
        this.estrategiaRepository = estrategiaRepository;
    }

    @Override
    public EstrategiaDTO save(EstrategiaDTO estrategiaDTO) {
        Estrategia estrategia = Estrategia.builder()
                .nombre(estrategiaDTO.getNombre())
                .build();

        return EstrategiaDTO.fromEntity(estrategiaRepository.save(estrategia));
    }

    @Override
    public EstrategiaDTO update(EstrategiaDTO estrategiaDTO) {
        Estrategia estrategia = estrategiaRepository.findById(estrategiaDTO.getId())
                .orElseThrow(() -> new NotFoundException("Estrategia con ID " + estrategiaDTO.getId() + " no encontrado"));

        estrategia.setNombre(estrategiaDTO.getNombre());
        return EstrategiaDTO.fromEntity(estrategiaRepository.save(estrategia));
    }

    @Override
    public EstrategiaDTO findById(Long id) {
        Estrategia estrategia = estrategiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estrategia con ID " + id + " no encontrado"));
        return EstrategiaDTO.fromEntity(estrategia);
    }

    @Override
    public void deleteById(Long id) {
        Estrategia estrategia = estrategiaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estrategia con ID " + id + " no encontrado"));
        estrategiaRepository.delete(estrategia);
    }

    @Override
    public List<EstrategiaDTO> findAll() {
        List<Estrategia> estrategias = estrategiaRepository.findAll();
        return estrategias.stream().map(EstrategiaDTO::fromEntity).toList();
    }
}
