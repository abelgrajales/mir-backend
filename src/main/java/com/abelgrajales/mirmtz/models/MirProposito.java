package com.abelgrajales.mirmtz.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "mir_proposito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MirProposito {
    @Id
    @SequenceGenerator(name = "seq_mir_proposito", sequenceName = "seq_mir_proposito", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mir_proposito")
    private Long id;

    @Column(name = "resumen_narrativo")
    private String resumenNarrativo;

    private String supuestos;

    @OneToOne
    @JoinColumn(name = "programa_id")
    private Programa programa;

    @OneToMany(mappedBy = "mirProposito", cascade = CascadeType.ALL)
    private List<Indicador> indicadores;
}