package ru.digitalleague.jasperpfs.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.digitalleague.jasperpfs.dto.TestPfDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JasperReportServiceImpl implements JasperReportService {

    private static final String EXTENSION = ".jrxml";

    @Override
    public byte[] generate(TestPfDto input) {
        JasperReport jasperReport = compileJasperReport("test");   //Тут надо можно изменить как то на параметр
        JasperPrint jasperPrint = generateTemplate(jasperReport, input);
        return exportTemplateToByteArray(jasperPrint);
    }


    public JasperReport compileJasperReport(String templateName) {
        JasperReport jasperReport = null;
        try (InputStream reportStream = new ClassPathResource(templateName + EXTENSION).getInputStream()) {
            log.debug("InputStream: " + reportStream.getClass());
            jasperReport = JasperCompileManager.compileReport(reportStream);
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }

        return jasperReport;
    }

    public JasperPrint generateTemplate(JasperReport jasperReport, TestPfDto dto) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", dto.getField());
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        } catch (JRException e) {
            e.printStackTrace();
        }
        return jasperPrint;
    }

    public byte[] exportTemplateToByteArray(JasperPrint jasperPrint) {
        byte[] byteArray = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

}
