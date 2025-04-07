package com.abelgrajales.mirmtz.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "centro_gestor")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CentroGestor {

    @Id
    @SequenceGenerator(name = "seq_centro_gestor", sequenceName = "seq_centro_gestor", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_centro_gestor")
    private Long id;

    private String nombre;
}
