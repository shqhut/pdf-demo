package com.shq.pdf.controller;

import com.shq.pdf.utils.HtmlUtils;
import freemarker.template.TemplateException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
public class PdfController {

    @RequestMapping(value = "/print/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> printPdf() throws Exception {
        Map<String, Object> mapData = new HashMap<>();
        String templateContent = HtmlUtils.getTemplateContent("test.ftl", mapData);
//        ResponseEntity response = this.getDownloadResponse(HtmlUtils.html2Pdf(templateContent), "device info.pdf");
        return null;
    }
}
