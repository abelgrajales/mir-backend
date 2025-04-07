package com.abelgrajales.mirmtz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProgramaRequest {

    private Long id;

    @NotBlank(message = "El nombre del programa no puede estar vacío")
    private String nombre;

    @NotNull(message = "Debe seleccionar un eje")
    private Long ejeId;

    @NotNull(message = "Debe seleccionar un subtema")
    private Long subtemaId;

    @NotNull(message = "Debe seleccionar una estrategia")
    private Long estrategiaId;

    @NotNull(message = "Debe seleccionar un centro gestor")
    private Long centroGestorId;
}
