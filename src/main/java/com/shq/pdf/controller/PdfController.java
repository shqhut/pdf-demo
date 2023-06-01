package com.shq.pdf.controller;

import com.shq.pdf.service.PdfTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PdfController {

    @Autowired
    PdfTestService pdfTestService;

    @RequestMapping(value = "/print/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> printPdf() throws Exception {
        return pdfTestService.findPdf();
    }


}
