package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.MirComponente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MirComponenteDTO {

    private Long id;
    private String resumenNarrativo;
    private String supuestos;
    private IndicadorDTO indicador;

    public static MirComponenteDTO fromEntity(MirComponente mirComponente) {
        MirComponenteDTO dto = new MirComponenteDTO();
        dto.setId(mirComponente.getId());
        dto.setResumenNarrativo(mirComponente.getResumenNarrativo());
        dto.setSupuestos(mirComponente.getSupuestos());
        dto.setIndicador(IndicadorDTO.fromEntity(mirComponente.getIndicador()));
        return dto;
    }
}
