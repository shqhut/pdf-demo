package com.shq.pdf.service;

import com.shq.pdf.PdfApplication;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PdfTestService {

    public ResponseEntity<byte[]> findPdf() throws IOException {
        // 模版所需变量
        Map<String,Object> data = new HashMap<>();
        // FreeMarker 生成 html字符串
        Configuration conf = new Configuration(Configuration.VERSION_2_3_23);
        conf.setDefaultEncoding("UTF-8");
        // 加载模版文件
        // 项目打包为jar包只能使用setClassForTemplateLoading方法
        conf.setClassForTemplateLoading(PdfApplication.class,"/pdfTemplate");
        // 加载模版
        Template template = conf.getTemplate("test.ftl");

        return new ResponseEntity<>(new byte[3], HttpStatus.OK);
    }

}
