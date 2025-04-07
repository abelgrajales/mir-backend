package com.abelgrajales.mirmtz.dto;

import com.abelgrajales.mirmtz.models.Programa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProgramaDTO {

    private Long id;
    private String nombre;
    private EjeDTO eje;
    private SubtemaDTO subtema;
    private EstrategiaDTO estrategia;
    private CentroGestorDTO centroGestor;

    private MirFinDTO fin;
    private MirPropositoDTO proposito;
    private List<MirComponenteDTO> componentes;
    private List<MirActividadDTO> actividades;

    public static ProgramaDTO fromEntity(Programa programa) {
        ProgramaDTO dto = new ProgramaDTO();
        dto.setId(programa.getId());
        dto.setNombre(programa.getNombre());
        dto.setEje(EjeDTO.fromEntity(programa.getEje()));
        dto.setSubtema(SubtemaDTO.fromEntity(programa.getSubtema()));
        dto.setEstrategia(EstrategiaDTO.fromEntity(programa.getEstrategia()));
        dto.setCentroGestor(CentroGestorDTO.fromEntity(programa.getCentroGestor()));
        return dto;
    }

    public static ProgramaDTO fromEntityAll(Programa programa) {
        ProgramaDTO dto = fromEntity(programa);
        dto.setFin(MirFinDTO.fromEntity(programa.getFin()));
        dto.setProposito(MirPropositoDTO.fromEntity(programa.getProposito()));
        dto.setComponentes(programa.getComponentes().stream().map(MirComponenteDTO::fromEntity).toList());
        dto.setActividades(programa.getActividades().stream().map(MirActividadDTO::fromEntity).toList());
        return dto;
    }
}
