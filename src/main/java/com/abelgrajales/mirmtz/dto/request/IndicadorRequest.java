package com.abelgrajales.mirmtz.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicadorRequest {

    private Long id;

    @NotBlank(message = "Debe seleccionar un indicador")
    private String tipoIndicador;

    @NotBlank(message = "El nombre del indicador no puede estar vacío")
    private String nombreIndicador;

    private String descripcionIndicador;

    @NotBlank(message = "Debe seleccionar una dimensión")
    private String dimension;

    @NotBlank(message = "Debe seleccionar una frecuencia")
    private String frecuencia;

    @NotBlank(message = "Debe seleccionar un algoritmo")
    private String algoritmo;

    private String mediosVerificacion;

    private String nombreVariableA;
    private String unidadMedidaA;
    private Long valorProgramadoA;

    private String nombreVariableB;
    private String unidadMedidaB;
    private Long valorProgramadoB;

    private Long optimoMinimo;

    private Long optimoMaximo;

    private Long procesoMinimo;

    private Long procesoMaximo;

    private Long rezagoMinimo;

    private Long rezagoMaximo;

    private String tipoAsociacion;
    private Long asociacionId;
}
