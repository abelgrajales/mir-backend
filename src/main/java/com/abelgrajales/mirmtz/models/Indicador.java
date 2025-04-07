package com.abelgrajales.mirmtz.models;

import com.abelgrajales.mirmtz.enums.Algoritmo;
import com.abelgrajales.mirmtz.enums.Dimension;
import com.abelgrajales.mirmtz.enums.Frecuencia;
import com.abelgrajales.mirmtz.enums.TipoIndicador;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "mir_indicador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Indicador {

    @Id
    @SequenceGenerator(name = "seq_mir_indicador", sequenceName = "seq_mir_indicador", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mir_indicador")
    private Long id;

    @Column(name = "tipo_indicador")
    @Enumerated(EnumType.STRING)
    private TipoIndicador tipoIndicador;

    private String nombre;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Dimension dimension;

    @Enumerated(EnumType.STRING)
    private Frecuencia frecuencia;

    @Enumerated(EnumType.STRING)
    private Algoritmo algoritmo;

    @OneToMany(mappedBy = "indicador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VariableIndicador> variables;

    @Column(name = "meta")
    private Long meta;

    @Column(name = "medios_verificacion")
    private String mediosVerificacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "umbral_indicador_id")
    private UmbralIndicador umbralIndicador;

    @ManyToOne
    @JoinColumn(name = "mir_fin_id")
    private MirFin mirFin;

    @ManyToOne
    @JoinColumn(name = "mir_proposito_id")
    private MirProposito mirProposito;

    @OneToOne(mappedBy = "indicador")
    private MirComponente mirComponente;

    @OneToOne(mappedBy = "indicador")
    private MirActividad mirActividad;
}
