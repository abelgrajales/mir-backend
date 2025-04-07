package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.UmbralIndicador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UmbralIndicadorDTO {

    private Long id;
    private Long optimoMinimo;
    private Long optimoMaximo;
    private Long procesoMinimo;
    private Long procesoMaximo;
    private Long rezagoMinimo;
    private Long rezagoMaximo;

    public static UmbralIndicadorDTO fromEntity(UmbralIndicador umbral) {
        if (umbral == null) {
            return null;
        }
        UmbralIndicadorDTO dto = new UmbralIndicadorDTO();
        dto.setId(umbral.getId());
        dto.setOptimoMinimo(umbral.getOptimoMinimo());
        dto.setOptimoMaximo(umbral.getOptimoMaximo());
        dto.setProcesoMinimo(umbral.getProcesoMinimo());
        dto.setProcesoMaximo(umbral.getProcesoMaximo());
        dto.setRezagoMinimo(umbral.getRezagoMinimo());
        dto.setRezagoMaximo(umbral.getRezagoMaximo());
        return dto;
    }
}
