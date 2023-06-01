package com.shq.pdf.utils;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.font.FontProvider;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class HtmlUtils {

    /**
     * @return
     * @throws Exception
     */
    public static String getTemplateDirectory() {
        ClassLoader classLoader = HtmlUtils.class.getClassLoader();
        URL resource = classLoader.getResource("templates");
        try {
            return Objects.requireNonNull(resource).toURI().getPath();
        } catch (URISyntaxException e) {
            log.error("获取模板文件夹失败,{}", e);
        }
        return null;
    }

    /**
     * 获取模板内容
     *
     * @param templateName 模板文件名
     * @param paramMap     模板参数
     * @return
     * @throws Exception
     */
    public static String getTemplateContent(String templateName, Map<String, Object> paramMap) throws Exception {
        Configuration config = ApplicationContextUtil.getBean(FreeMarkerConfigurer.class).getConfiguration();
        config.setDefaultEncoding("UTF-8");
        Template template = config.getTemplate(templateName, "UTF-8");
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, paramMap);
    }

    /**
     * HTML 转 PDF
     *
     * @param content html内容
     * @param outPath 输出pdf路径
     * @return 是否创建成功
     */
    public static boolean html2Pdf(String content, String outPath) {
        try {
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setCharset("UTF-8");
            FontProvider fontProvider = new FontProvider();
            fontProvider.addSystemFonts();
            converterProperties.setFontProvider(fontProvider);
            HtmlConverter.convertToPdf(content, new FileOutputStream(outPath), converterProperties);
        } catch (Exception e) {
            log.error("生成模板内容失败,{}", e);
            return false;
        }
        return true;
    }

    /**
     * HTML 转 PDF
     *
     * @param content html内容
     * @return PDF字节数组
     */
    public static ByteArrayOutputStream html2Pdf(String content) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setCharset("UTF-8");
            FontProvider fontProvider = new FontProvider();
            fontProvider.addSystemFonts();
            converterProperties.setFontProvider(fontProvider);
            HtmlConverter.convertToPdf(content, outputStream, converterProperties);
        } catch (Exception e) {
            log.error("生成 PDF 失败,{}", e);
        }
        return outputStream;
    }
}
