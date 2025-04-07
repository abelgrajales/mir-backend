package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.MirProposito;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MirPropositoDTO {

    private Long id;
    private String resumenNarrativo;
    private String supuestos;

    public static MirPropositoDTO fromEntity(MirProposito mirProposito) {
        MirPropositoDTO dto = new MirPropositoDTO();
        dto.setId(mirProposito.getId());
        dto.setResumenNarrativo(mirProposito.getResumenNarrativo());
        dto.setSupuestos(mirProposito.getSupuestos());
        return dto;
    }
}
