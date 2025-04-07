package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.MirComponenteDTO;
import com.abelgrajales.mirmtz.dto.request.MirActividadComponenteRequest;

public interface MirComponenteService {

    MirComponenteDTO save(MirActividadComponenteRequest request);

    MirComponenteDTO update(MirActividadComponenteRequest request);

    MirComponenteDTO findById(Long id);

    void deleteById(Long id);
}
