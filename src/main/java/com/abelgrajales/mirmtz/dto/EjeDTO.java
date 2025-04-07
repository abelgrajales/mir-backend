package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.Eje;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EjeDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    public static EjeDTO fromEntity(Eje eje) {
        EjeDTO dto = new EjeDTO();
        dto.setId(eje.getId());
        dto.setNombre(eje.getNombre());
        return dto;
    }
}
