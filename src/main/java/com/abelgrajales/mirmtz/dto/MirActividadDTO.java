package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.MirActividad;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MirActividadDTO {

    private Long id;
    private String resumenNarrativo;
    private String supuestos;
    private IndicadorDTO indicador;

    public static MirActividadDTO fromEntity(MirActividad mirActividad) {
        MirActividadDTO dto = new MirActividadDTO();
        dto.setId(mirActividad.getId());
        dto.setResumenNarrativo(mirActividad.getResumenNarrativo());
        dto.setSupuestos(mirActividad.getSupuestos());
        dto.setIndicador(IndicadorDTO.fromEntity(mirActividad.getIndicador()));
        return dto;
    }
}
