package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.Indicador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class IndicadorDTO {

    private Long id;
    private String tipoIndicador;
    private String nombre;
    private String descripcion;
    private String dimension;
    private String frecuencia;
    private String algoritmo;
    private Long meta;
    private String mediosVerificacion;

    private UmbralIndicadorDTO umbralIndicador;
    private List<VariableIndicadorDTO> variables;

    public static IndicadorDTO fromEntity(Indicador indicador) {
        if (indicador == null) {
            return null;
        }

        IndicadorDTO dto = new IndicadorDTO();
        dto.setId(indicador.getId());
        dto.setNombre(indicador.getNombre());
        dto.setDescripcion(indicador.getDescripcion());
        dto.setMeta(indicador.getMeta());
        dto.setMediosVerificacion(indicador.getMediosVerificacion());

        if (indicador.getTipoIndicador() != null) {
            dto.setTipoIndicador(indicador.getTipoIndicador().getTipo());
        }

        if (indicador.getDimension() != null) {
            dto.setDimension(indicador.getDimension().getDimension());
        }

        if (indicador.getFrecuencia() != null) {
            dto.setFrecuencia(indicador.getFrecuencia().name());
        }

        if (indicador.getAlgoritmo() != null) {
            dto.setAlgoritmo(indicador.getAlgoritmo().getFormula());
        }

        if (indicador.getUmbralIndicador() != null) {
            dto.setUmbralIndicador(UmbralIndicadorDTO.fromEntity(indicador.getUmbralIndicador()));
        }

        if (indicador.getVariables() != null && !indicador.getVariables().isEmpty()) {
            dto.setVariables(indicador.getVariables().stream()
                    .map(VariableIndicadorDTO::fromEntity)
                    .toList());
        } else {
            dto.setVariables(new ArrayList<>());
        }

        return dto;
    }
}
