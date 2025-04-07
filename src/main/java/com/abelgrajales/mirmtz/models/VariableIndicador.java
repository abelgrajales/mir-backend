package com.abelgrajales.mirmtz.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "variable_indicador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariableIndicador {

    @Id
    @SequenceGenerator(name = "seq_mir_variable", sequenceName = "seq_mir_variable", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mir_variable")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "indicador_id")
    private Indicador indicador;

    @Enumerated(EnumType.STRING)
    private TipoVariable tipo;

    private String nombre;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "valor_programado")
    private Long valorProgramado;

    public enum TipoVariable {
        VARIABLE_A, VARIABLE_B
    }
}
