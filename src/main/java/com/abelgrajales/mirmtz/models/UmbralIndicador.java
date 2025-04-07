package com.abelgrajales.mirmtz.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "umbral_indicador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UmbralIndicador {

    @Id
    @SequenceGenerator(name = "seq_mir_umbral", sequenceName = "seq_mir_umbral", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mir_umbral")
    private Long id;

    @Column(name = "optimo_minimo")
    private Long optimoMinimo;

    @Column(name = "optimo_maximo")
    private Long optimoMaximo;

    @Column(name = "proceso_minimo")
    private Long procesoMinimo;

    @Column(name = "proceso_maximo")
    private Long procesoMaximo;

    @Column(name = "rezago_minimo")
    private Long rezagoMinimo;

    @Column(name = "rezago_maximo")
    private Long rezagoMaximo;
}
