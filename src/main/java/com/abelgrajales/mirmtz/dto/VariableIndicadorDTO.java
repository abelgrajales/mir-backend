package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.VariableIndicador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VariableIndicadorDTO {

    private Long id;
    private String tipo;
    private String nombre;
    private String unidadMedida;
    private Long valorProgramado;

    public static VariableIndicadorDTO fromEntity(VariableIndicador variable) {
        if (variable == null) {
            return null;
        }

        VariableIndicadorDTO dto = new VariableIndicadorDTO();
        dto.setId(variable.getId());
        dto.setNombre(variable.getNombre());
        dto.setUnidadMedida(variable.getUnidadMedida());
        dto.setValorProgramado(variable.getValorProgramado());
        if (variable.getTipo() != null) {
            dto.setTipo(variable.getTipo().name());
        }
        return dto;
    }
}
