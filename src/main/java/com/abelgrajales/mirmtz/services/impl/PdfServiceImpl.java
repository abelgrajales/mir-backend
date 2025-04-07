package com.abelgrajales.mirmtz.services.impl;

import com.abelgrajales.mirmtz.exception.BusinessException;
import com.abelgrajales.mirmtz.models.*;
import com.abelgrajales.mirmtz.repositories.ProgramaRepository;
import com.abelgrajales.mirmtz.services.PdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {

    private final ProgramaRepository programaRepository;

    private static final BaseColor COLOR_PANTONE = WebColors.getRGBColor("#9D2449");
    private static final Font HEADER_FONT = FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.WHITE);
    private static final Font TITLE_FONT = FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK);

    @Autowired
    public PdfServiceImpl(ProgramaRepository programaRepository) {
        this.programaRepository = programaRepository;
    }

    @Override
    public byte[] generatePdf(Long idPrograma) {
        Programa programa = programaRepository.findById(idPrograma)
                .orElseThrow(() -> new BusinessException("Programa no encontrado con id: " + idPrograma));

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            Document document = crearDocumento(byteArrayOutputStream);

            agregarTitulo(document);
            agregarInfoPrograma(document, programa);
            agregarTablaIndicadores(document, programa);

            document.close();
            return byteArrayOutputStream.toByteArray();
        } catch (DocumentException e) {
            throw new BusinessException("Error al generar el PDF: " + e.getMessage());
        } catch (IOException e) {
            throw new BusinessException("Error al cerrar el flujo de salida: " + e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("Error inesperado: " + e.getMessage());
        }
    }

    private Document crearDocumento(ByteArrayOutputStream outputStream) throws DocumentException {
        Document document = new Document();
        document.setPageSize(PageSize.A3.rotate());
        PdfWriter.getInstance(document, outputStream);
        document.open();
        return document;
    }

    private void agregarTitulo(Document document) throws DocumentException {
        Paragraph titulo = new Paragraph("Matriz de indicadores para resultados", TITLE_FONT);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Chunk(""));
    }

    private void agregarInfoPrograma(Document document, Programa programa) throws DocumentException {
        PdfPTable tabla = new PdfPTable(2);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new int[]{1, 10});

        agregarFilaInfo(tabla, "Centro gestor", programa.getCentroGestor().getNombre());
        agregarFilaInfo(tabla, "Eje", programa.getEje().getNombre());
        agregarFilaInfo(tabla, "Subtema", programa.getSubtema().getNombre());
        agregarFilaInfo(tabla, "Estrategia", programa.getEstrategia().getNombre());
        agregarFilaInfo(tabla, "Programa", programa.getNombre());

        document.add(tabla);
    }

    private void agregarFilaInfo(PdfPTable tabla, String etiqueta, String valor) {
        PdfPCell cell = crearCeldaEncabezado(etiqueta);
        tabla.addCell(cell);
        tabla.addCell(valor);
    }

    private PdfPCell crearCeldaEncabezado(String texto) {
        PdfPCell cell = new PdfPCell(new Paragraph(texto, HEADER_FONT));
        cell.setBackgroundColor(COLOR_PANTONE);
        cell.setPadding(5);
        return cell;
    }

    private void agregarTablaIndicadores(Document document, Programa programa) throws DocumentException {
        PdfPTable tabla = new PdfPTable(15);
        tabla.setSpacingBefore(20);
        tabla.setSpacingAfter(20);
        tabla.setWidthPercentage(100);

        agregarEncabezadosTabla(tabla);
        agregarNivelFin(tabla, programa.getFin());
        agregarNivelProposito(tabla, programa.getProposito());
        agregarNivelComponentes(tabla, programa.getComponentes());
        agregarNivelActividades(tabla, programa.getActividades());

        document.add(tabla);
    }

    private void agregarEncabezadosTabla(PdfPTable tabla) {
        PdfPCell cellNivel = crearCeldaEncabezado("Nivel");
        cellNivel.setRowspan(2);
        tabla.addCell(cellNivel);

        PdfPCell cellResumen = crearCeldaEncabezado("Resumen narrativo");
        cellResumen.setRowspan(2);
        tabla.addCell(cellResumen);

        PdfPCell cellIndicadores = crearCeldaEncabezado("Indicadores");
        cellIndicadores.setColspan(12);
        tabla.addCell(cellIndicadores);

        PdfPCell cellSupuestos = crearCeldaEncabezado("Supuestos del indicador");
        cellSupuestos.setRowspan(2);
        tabla.addCell(cellSupuestos);

        String[] columnas = {
                "Nombre", "Descripción", "Dimensión", "Tipo de indicador", "Algoritmo",
                "Valor programado A (Numerador)", "Unidad de medida A",
                "Valor programado B (Denominador)", "Unidad de medida B",
                "Meta del indicador", "Frecuencia", "Medios de verificación"
        };

        for (String columna : columnas) {
            tabla.addCell(crearCeldaEncabezado(columna));
        }
    }

    private void agregarNivelFin(PdfPTable tabla, MirFin mirFin) {
        PdfPCell cellNivel = new PdfPCell(new Phrase("Fin"));
        cellNivel.setRowspan(mirFin.getIndicadores().size());
        tabla.addCell(cellNivel);

        PdfPCell cellResumen = new PdfPCell(new Phrase(mirFin.getResumenNarrativo()));
        cellResumen.setRowspan(mirFin.getIndicadores().size());
        tabla.addCell(cellResumen);

        agregarIndicadores(tabla, mirFin.getIndicadores(), mirFin.getSupuestos());
    }

    private void agregarNivelProposito(PdfPTable tabla, MirProposito mirProposito) {
        PdfPCell cellNivel = new PdfPCell(new Phrase("Proposito"));
        cellNivel.setRowspan(mirProposito.getIndicadores().size());
        tabla.addCell(cellNivel);

        PdfPCell cellResumen = new PdfPCell(new Phrase(mirProposito.getResumenNarrativo()));
        cellResumen.setRowspan(mirProposito.getIndicadores().size());
        tabla.addCell(cellResumen);

        agregarIndicadores(tabla, mirProposito.getIndicadores(), mirProposito.getSupuestos());
    }

    private void agregarNivelComponentes(PdfPTable tabla, List<MirComponente> componentes) {
        for (MirComponente componente : componentes) {
            tabla.addCell(new Phrase("Componente"));
            tabla.addCell(new Phrase(componente.getResumenNarrativo()));
            agregarDatosIndicador(tabla, componente.getIndicador());
            tabla.addCell(componente.getSupuestos());
        }
    }

    private void agregarNivelActividades(PdfPTable tabla, List<MirActividad> actividades) {
        for (MirActividad actividad : actividades) {
            tabla.addCell(new Phrase("Actividades"));
            tabla.addCell(new Phrase(actividad.getResumenNarrativo()));
            agregarDatosIndicador(tabla, actividad.getIndicador());
            tabla.addCell(actividad.getSupuestos());
        }
    }

    private void agregarIndicadores(PdfPTable tabla, List<Indicador> indicadores, String supuestos) {
        boolean primeraFila = true;

        for (Indicador indicador : indicadores) {
            agregarDatosIndicador(tabla, indicador);

            if (primeraFila) {
                PdfPCell cellSupuestos = new PdfPCell(new Phrase(supuestos));
                cellSupuestos.setRowspan(indicadores.size());
                tabla.addCell(cellSupuestos);
                primeraFila = false;
            }
        }
    }

    private void agregarDatosIndicador(PdfPTable tabla, Indicador indicador) {
        tabla.addCell(indicador.getNombre());
        tabla.addCell(indicador.getDescripcion());
        tabla.addCell(indicador.getDimension().getDimension());
        tabla.addCell(indicador.getTipoIndicador().getTipo());
        tabla.addCell(indicador.getAlgoritmo().getFormula());

        agregarDatosVariables(tabla, indicador.getVariables());

        tabla.addCell(String.valueOf(indicador.getMeta()));
        tabla.addCell(indicador.getFrecuencia().toString());
        tabla.addCell(indicador.getMediosVerificacion());
    }

    private void agregarDatosVariables(PdfPTable tabla, List<VariableIndicador> variables) {
        String valorA = "", unidadA = "", valorB = "", unidadB = "";

        for (VariableIndicador variable : variables) {
            if (variable.getTipo() == VariableIndicador.TipoVariable.VARIABLE_A) {
                valorA = variable.getValorProgramado() != null ? String.valueOf(variable.getValorProgramado()) : "";
                unidadA = variable.getUnidadMedida() != null && !variable.getUnidadMedida().isEmpty() ?
                        variable.getUnidadMedida() : "";
            } else if (variable.getTipo() == VariableIndicador.TipoVariable.VARIABLE_B) {
                valorB = variable.getValorProgramado() != null ? String.valueOf(variable.getValorProgramado()) : "";
                unidadB = variable.getUnidadMedida() != null && !variable.getUnidadMedida().isEmpty() ?
                        variable.getUnidadMedida() : "";
            }
        }

        tabla.addCell(valorA);
        tabla.addCell(unidadA);
        tabla.addCell(valorB);
        tabla.addCell(unidadB);
    }
}
