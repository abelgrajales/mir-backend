package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.CentroGestor;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CentroGestorDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    public static CentroGestorDTO fromEntity(CentroGestor centroGestor) {
        CentroGestorDTO dto = new CentroGestorDTO();
        dto.setId(centroGestor.getId());
        dto.setNombre(centroGestor.getNombre());
        return dto;
    }
}
