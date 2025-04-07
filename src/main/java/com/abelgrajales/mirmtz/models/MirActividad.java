package com.abelgrajales.mirmtz.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mir_actividad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MirActividad {

    @Id
    @SequenceGenerator(name = "seq_mir_actividad", sequenceName = "seq_mir_actividad", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mir_actividad")
    private Long id;

    @Column(name = "resumen_narrativo")
    private String resumenNarrativo;

    private String supuestos;

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private Programa programa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "indicador_id")
    private Indicador indicador;

}
