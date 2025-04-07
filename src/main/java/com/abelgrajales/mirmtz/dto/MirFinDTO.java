package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.MirFin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MirFinDTO {

    private Long id;
    private String resumenNarrativo;
    private String supuestos;



    public static MirFinDTO fromEntity(MirFin mirFin) {
        MirFinDTO dto = new MirFinDTO();
        dto.setId(mirFin.getId());
        dto.setResumenNarrativo(mirFin.getResumenNarrativo());
        dto.setSupuestos(mirFin.getSupuestos());
        return dto;
    }
}
