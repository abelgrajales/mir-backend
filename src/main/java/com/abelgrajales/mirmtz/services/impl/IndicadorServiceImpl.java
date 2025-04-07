package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.dto.IndicadorDTO;
import com.abelgrajales.mirmtz.dto.request.IndicadorRequest;
import com.abelgrajales.mirmtz.enums.Algoritmo;
import com.abelgrajales.mirmtz.enums.Dimension;
import com.abelgrajales.mirmtz.enums.Frecuencia;
import com.abelgrajales.mirmtz.enums.TipoIndicador;
import com.abelgrajales.mirmtz.exception.BusinessException;
import com.abelgrajales.mirmtz.exception.NotFoundException;
import com.abelgrajales.mirmtz.models.*;
import com.abelgrajales.mirmtz.repositories.IndicadorRepository;
import com.abelgrajales.mirmtz.repositories.MirFinRepository;
import com.abelgrajales.mirmtz.repositories.MirPropositoRepository;
import com.abelgrajales.mirmtz.services.IndicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndicadorServiceImpl implements IndicadorService {

    private final IndicadorRepository indicadorRepository;
    private final MirFinRepository mirFinRepository;
    private final MirPropositoRepository mirPropositoRepository;

    @Autowired
    public IndicadorServiceImpl(IndicadorRepository indicadorRepository, MirFinRepository mirFinRepository, MirPropositoRepository mirPropositoRepository) {
        this.indicadorRepository = indicadorRepository;
        this.mirFinRepository = mirFinRepository;
        this.mirPropositoRepository = mirPropositoRepository;
    }

    @Override
    public IndicadorDTO save(IndicadorRequest request) {
        Indicador indicador = new Indicador();
        actualizarIndicador(indicador, request);

        if ("FIN".equals(request.getTipoAsociacion())) {
            MirFin mirFin = mirFinRepository.findById(request.getAsociacionId())
                    .orElseThrow(() -> new NotFoundException("Nivel Fin con ID " + request.getAsociacionId() + " no encontrado"));
            indicador.setMirFin(mirFin);

            if (mirFin.getIndicadores() == null) {
                mirFin.setIndicadores(new ArrayList<>());
            }
            mirFin.getIndicadores().add(indicador);

        } else if ("PROPOSITO".equals(request.getTipoAsociacion())) {
            MirProposito mirProposito = mirPropositoRepository.findById(request.getAsociacionId())
                    .orElseThrow(() -> new NotFoundException("Nivel Proposito con ID " + request.getAsociacionId() + " no encontrado"));
            indicador.setMirProposito(mirProposito);

            if (mirProposito.getIndicadores() == null) {
                mirProposito.setIndicadores(new ArrayList<>());
            }
            mirProposito.getIndicadores().add(indicador);
        } else {
            throw new BusinessException("Tipo de asociación no válido: debe ser 'FIN' o 'PROPOSITO'");
        }

        Indicador savedIndicador = indicadorRepository.save(indicador);
        return IndicadorDTO.fromEntity(savedIndicador);
    }

    @Override
    public IndicadorDTO update(IndicadorRequest request) {
        Indicador indicador = indicadorRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Indicador con ID " + request.getId() + " no encontrado"));

        actualizarIndicador(indicador, request);

        if ("FIN".equals(request.getTipoAsociacion())) {
            MirFin mirFin = mirFinRepository.findById(request.getAsociacionId())
                    .orElseThrow(() -> new NotFoundException("Nivel Fin con ID " + request.getAsociacionId() + " no encontrado"));

            indicador.setMirFin(mirFin);
            if (mirFin.getIndicadores() == null) {
                mirFin.setIndicadores(new ArrayList<>());
            }
            mirFin.getIndicadores().add(indicador);
        } else if ("PROPOSITO".equals(request.getTipoAsociacion())) {
            MirProposito mirProposito = mirPropositoRepository.findById(request.getAsociacionId())
                    .orElseThrow(() -> new NotFoundException("Nivel Proposito con ID " + request.getAsociacionId() + " no encontrado"));

            indicador.setMirProposito(mirProposito);
            if (mirProposito.getIndicadores() == null) {
                mirProposito.setIndicadores(new ArrayList<>());
            }
            mirProposito.getIndicadores().add(indicador);
        } else {
            throw new BusinessException("Tipo de asociación no válido: debe ser 'FIN' o 'PROPOSITO'");
        }

        Indicador updatedIndicador = indicadorRepository.save(indicador);
        return IndicadorDTO.fromEntity(updatedIndicador);
    }

    @Override
    public IndicadorDTO findById(Long id) {
        Indicador indicador = indicadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Indicador con ID " + id + " no encontrado"));
        return IndicadorDTO.fromEntity(indicador);
    }

    @Override
    public List<IndicadorDTO> findByMirFin(Long id) {
        List<Indicador> indicadores = indicadorRepository.findByMirFin(id);
        return indicadores.stream().map(IndicadorDTO::fromEntity).toList();
    }

    @Override
    public List<IndicadorDTO> findByMirProposito(Long id) {
        List<Indicador> indicadores = indicadorRepository.findByMirProposito(id);
        return indicadores.stream().map(IndicadorDTO::fromEntity).toList();
    }

    @Override
    public void actualizarIndicador(Indicador indicador, IndicadorRequest request) {
        indicador.setTipoIndicador(TipoIndicador.fromTipo(request.getTipoIndicador()));
        indicador.setNombre(request.getNombreIndicador());
        indicador.setDescripcion(request.getDescripcionIndicador());
        indicador.setDimension(Dimension.fromDimension(request.getDimension()));
        indicador.setFrecuencia(Frecuencia.valueOf(request.getFrecuencia()));

        Algoritmo algoritmo = Algoritmo.fromFormula(request.getAlgoritmo());
        indicador.setAlgoritmo(algoritmo);

        Long meta = metaIndicador(
                request.getValorProgramadoA(),
                request.getValorProgramadoB(),
                algoritmo
        );
        indicador.setMeta(meta);
        indicador.setMediosVerificacion(request.getMediosVerificacion());

        actualizarUmbralIndicador(indicador, request);

        actualizarVariablesIndicador(indicador, request);
    }

    private void actualizarUmbralIndicador(Indicador indicador, IndicadorRequest request) {
        UmbralIndicador umbralIndicador = indicador.getUmbralIndicador();
        if (umbralIndicador == null) {
            umbralIndicador = buildUmbralIndicador(request);
            indicador.setUmbralIndicador(umbralIndicador);
        } else {
            umbralIndicador.setOptimoMinimo(request.getOptimoMinimo());
            umbralIndicador.setOptimoMaximo(request.getOptimoMaximo());
            umbralIndicador.setProcesoMinimo(request.getProcesoMinimo());
            umbralIndicador.setProcesoMaximo(request.getProcesoMaximo());
            umbralIndicador.setRezagoMinimo(request.getRezagoMinimo());
            umbralIndicador.setRezagoMaximo(request.getRezagoMaximo());
        }
    }

    private void actualizarVariablesIndicador(Indicador indicador, IndicadorRequest request) {
        if (indicador.getVariables() == null) {
            indicador.setVariables(new ArrayList<>());
        }

        VariableIndicador varA = null;
        VariableIndicador varB = null;

        for (VariableIndicador v : indicador.getVariables()) {
            if (v.getTipo() == VariableIndicador.TipoVariable.VARIABLE_A) {
                varA = v;
            } else if (v.getTipo() == VariableIndicador.TipoVariable.VARIABLE_B) {
                varB = v;
            }
        }

        if (request.getNombreVariableA() != null && !request.getNombreVariableA().isEmpty()) {
            if (varA != null) {
                varA.setNombre(request.getNombreVariableA());
                varA.setUnidadMedida(request.getUnidadMedidaA());
                varA.setValorProgramado(request.getValorProgramadoA());
            } else {
                varA = VariableIndicador.builder()
                        .tipo(VariableIndicador.TipoVariable.VARIABLE_A)
                        .nombre(request.getNombreVariableA())
                        .unidadMedida(request.getUnidadMedidaA())
                        .valorProgramado(request.getValorProgramadoA())
                        .indicador(indicador)
                        .build();
                indicador.getVariables().add(varA);
            }
        } else if (varA != null) {
            indicador.getVariables().remove(varA);
        }

        if (request.getNombreVariableB() != null && !request.getNombreVariableB().isEmpty()) {
            if (varB != null) {
                varB.setNombre(request.getNombreVariableB());
                varB.setUnidadMedida(request.getUnidadMedidaB());
                varB.setValorProgramado(request.getValorProgramadoB());
            } else {
                varB = VariableIndicador.builder()
                        .tipo(VariableIndicador.TipoVariable.VARIABLE_B)
                        .nombre(request.getNombreVariableB())
                        .unidadMedida(request.getUnidadMedidaB())
                        .valorProgramado(request.getValorProgramadoB())
                        .indicador(indicador)
                        .build();
                indicador.getVariables().add(varB);
            }
        } else if (varB != null) {
            indicador.getVariables().remove(varB);
        }
    }

    private UmbralIndicador buildUmbralIndicador(IndicadorRequest request) {
        return UmbralIndicador.builder()
                .optimoMinimo(request.getOptimoMinimo())
                .optimoMaximo(request.getOptimoMaximo())
                .procesoMinimo(request.getProcesoMinimo())
                .procesoMaximo(request.getProcesoMaximo())
                .rezagoMinimo(request.getRezagoMinimo())
                .rezagoMaximo(request.getRezagoMaximo())
                .build();
    }

    private Long metaIndicador(Long valorProgramadoA, Long valorProgramadoB, Algoritmo algoritmo) {
        if (valorProgramadoA == null) {
            return 0L;
        }

        if (algoritmo != Algoritmo.A && (valorProgramadoB == null || valorProgramadoB == 0)) {
            return 0L;
        }

        double resultado;

        switch (algoritmo) {
            case A_DIVIDIDO_B_POR_100:
                resultado = ((double) valorProgramadoA / valorProgramadoB) * 100;
                break;
            case A_DIVIDIDO_B_MENOS_1_POR_100:
                resultado = (((double) valorProgramadoA / valorProgramadoB) - 1) * 100;
                break;
            case A_DIVIDIDO_B:
                resultado = (double) valorProgramadoA / valorProgramadoB;
                break;
            case A:
            default:
                resultado = valorProgramadoA;
                break;
        }
        return Math.round(resultado);
    }
}
