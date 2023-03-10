package com.cositas.demo.service;

import com.cositas.demo.controller.DTO.CharacterDTO;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
@Service
public class PdfGenerator {

    public String parseThymeleafTemplate(CharacterDTO characterDTO){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("uuid", characterDTO.getUuid());
        context.setVariable("name", characterDTO.getName());
        context.setVariable("className", characterDTO.getClassName());
        //context.setVariable("stats", characterDTO.getStats());
        context.setVariable("alignment", characterDTO.getAlignment());

        return templateEngine.process("template/character", context);
    }

    public byte[] generatePdfFromHtml(String html) throws IOException {
        String name = "character.pdf";
        ByteArrayOutputStream ou = new ByteArrayOutputStream();
        byte[] doc;

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(ou);
        doc = ou.toByteArray();

        ou.close();



        return doc;
    }
}
