package com.abelgrajales.mirmtz.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MirActividadComponenteRequest {

    private Long id;

    @NotBlank(message = "El resumen narrativo no puede estar vacío")
    private String resumenNarrativo;

    @NotBlank(message = "Los supuestos no pueden estar vacíos")
    private String supuestos;

    @NotBlank(message = "Debe seleccionar un programa")
    private Long programaId;

    private IndicadorRequest indicador;
}
