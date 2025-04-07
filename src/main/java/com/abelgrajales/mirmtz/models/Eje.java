package com.abelgrajales.mirmtz.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "eje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Eje {

    @Id
    @SequenceGenerator(name = "seq_eje", sequenceName = "seq_eje", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_eje")
    private Long id;

    private String nombre;

}
