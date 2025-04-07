package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.Subtema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubtemaDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    public static SubtemaDTO fromEntity(Subtema subtema) {
        SubtemaDTO dto = new SubtemaDTO();
        dto.setId(subtema.getId());
        dto.setNombre(subtema.getNombre());
        return dto;
    }
}
