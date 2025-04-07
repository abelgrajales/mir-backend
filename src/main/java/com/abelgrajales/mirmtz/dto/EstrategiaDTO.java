package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.Estrategia;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstrategiaDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    public static EstrategiaDTO fromEntity(Estrategia estrategia) {
        EstrategiaDTO dto = new EstrategiaDTO();
        dto.setId(estrategia.getId());
        dto.setNombre(estrategia.getNombre());
        return dto;
    }
}
