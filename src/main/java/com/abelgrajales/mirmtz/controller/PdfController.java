package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private final PdfService pdfService;

    @Autowired
    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/generate/{idPrograma}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long idPrograma) {
        byte[] pdfBytes = pdfService.generatePdf(idPrograma);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=mir.pdf")
                .body(pdfBytes);
    }
}
