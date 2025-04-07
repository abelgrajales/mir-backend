package com.abelgrajales.mirmtz.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "programa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Programa {

    @Id
    @SequenceGenerator(name = "seq_programa", sequenceName = "seq_programa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_programa")
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "eje_id")
    private Eje eje;

    @ManyToOne
    @JoinColumn(name = "subtema_id")
    private Subtema subtema;

    @ManyToOne
    @JoinColumn(name = "estrategia_id")
    private Estrategia estrategia;

    @ManyToOne
    @JoinColumn(name = "centro_gestor_id")
    private CentroGestor centroGestor;

    @OneToOne(mappedBy = "programa", cascade = CascadeType.ALL)
    private MirFin fin;

    @OneToOne(mappedBy = "programa", cascade = CascadeType.ALL)
    private MirProposito proposito;

    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL)
    private List<MirComponente> componentes;

    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL)
    private List<MirActividad> actividades;

}
