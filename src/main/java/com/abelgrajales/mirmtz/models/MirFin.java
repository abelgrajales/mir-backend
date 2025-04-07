package com.abelgrajales.mirmtz.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "mir_fin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MirFin {

    @Id
    @SequenceGenerator(name = "seq_mir_fin", sequenceName = "seq_mir_fin", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mir_fin")
    private Long id;

    @Column(name = "resumen_narrativo")
    private String resumenNarrativo;

    private String supuestos;

    @OneToOne
    @JoinColumn(name = "programa_id")
    private Programa programa;

    @OneToMany(mappedBy = "mirFin", cascade = CascadeType.ALL)
    private List<Indicador> indicadores;
}
