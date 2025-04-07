package com.abelgrajales.mirmtz.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estrategia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estrategia {

    @Id
    @SequenceGenerator(name = "seq_estrategia", sequenceName = "seq_estrategia", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estrategia")
    private Long id;

    private String nombre;
}
