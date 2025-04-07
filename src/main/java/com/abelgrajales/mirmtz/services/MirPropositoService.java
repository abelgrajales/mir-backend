package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.MirPropositoDTO;
import com.abelgrajales.mirmtz.dto.request.MirFinPropositoRequest;

public interface MirPropositoService {

    MirPropositoDTO save(MirFinPropositoRequest request);

    MirPropositoDTO update(MirFinPropositoRequest request);

    MirPropositoDTO findById(Long id);
}
