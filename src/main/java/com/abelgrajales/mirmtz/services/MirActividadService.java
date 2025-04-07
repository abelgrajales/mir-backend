package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.MirActividadDTO;
import com.abelgrajales.mirmtz.dto.request.MirActividadComponenteRequest;

public interface MirActividadService {

    MirActividadDTO save(MirActividadComponenteRequest request);

    MirActividadDTO update(MirActividadComponenteRequest request);

    MirActividadDTO findById(Long id);

    void deleteById(Long id);
}
