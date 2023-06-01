package com.shq.pdf.service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.openhtmltopdf.extend.FSSupplier;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.shq.pdf.PdfApplication;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfTestService {

    public ResponseEntity<byte[]> findPdf() throws IOException, TemplateException {
        // 模版所需变量
        Map<String,Object> data = new HashMap<>();
        data.put("code", "这是测试内容！");
        // FreeMarker 生成 html字符串
        Configuration conf = new Configuration(Configuration.VERSION_2_3_23);
        conf.setDefaultEncoding("UTF-8");
        // 加载模版文件
        // 项目打包为jar包只能使用setClassForTemplateLoading方法
        conf.setClassForTemplateLoading(PdfApplication.class,"/pdfTemplate");
        // 加载模版
        Template template = conf.getTemplate("test.ftl");
        // 传入变量生成html字符串
        String htmlString = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
        @Cleanup ByteArrayOutputStream os = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(htmlString, "");
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        builder.useFont(new FSSupplier<InputStream>() {
            @SneakyThrows
            @Override
            public InputStream supply() {
                return resourceLoader.getResource("classpath:font/simsun.ttf").getInputStream();
            }
        }, "simsun");
        builder.toStream(os);
        builder.run();
        byte[] bytes = os.toByteArray();
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

}
