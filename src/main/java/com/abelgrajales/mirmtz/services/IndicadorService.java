package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.IndicadorDTO;
import com.abelgrajales.mirmtz.dto.request.IndicadorRequest;
import com.abelgrajales.mirmtz.models.Indicador;

import java.util.List;

public interface IndicadorService {

    IndicadorDTO save(IndicadorRequest request);

    IndicadorDTO update(IndicadorRequest request);

    IndicadorDTO findById(Long id);

    List<IndicadorDTO> findByMirFin(Long id);

    List<IndicadorDTO> findByMirProposito(Long id);

    void actualizarIndicador(Indicador indicador, IndicadorRequest request);
}
