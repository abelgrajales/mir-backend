package com.abelgrajales.mirmtz.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subtema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subtema {

    @Id
    @SequenceGenerator(name = "seq_subtema", sequenceName = "seq_subtema", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_subtema")
    private Long id;

    private String nombre;
}
