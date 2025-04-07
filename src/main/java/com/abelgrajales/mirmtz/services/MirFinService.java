package com.abelgrajales.mirmtz.services;

import com.abelgrajales.mirmtz.dto.MirFinDTO;
import com.abelgrajales.mirmtz.dto.request.MirFinPropositoRequest;

public interface MirFinService {

    MirFinDTO save(MirFinPropositoRequest request);

    MirFinDTO update(MirFinPropositoRequest request);

    MirFinDTO findById(Long id);
}
