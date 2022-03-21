package ru.digitalleague.jasperpfs;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.junit.jupiter.api.Test;
import ru.digitalleague.jasperpfs.dto.TestPfDto;
import ru.digitalleague.jasperpfs.service.JasperReportServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JasperReportServiceTest {

    //    @Value("${templateName})")
    public String templateName = "test";
    public JasperReport jasperReport;
    private JasperReportServiceImpl jasperReportServiceImpl = new JasperReportServiceImpl();
    private JasperPrint jasperPrint;
    private String FILE_EXTENSION = ".pdf";

    @Test
    public void CompileReportTest() {
        jasperReport = jasperReportServiceImpl.compileJasperReport("test");

        assertNotNull(jasperReport);
        jasperPrint = jasperReportServiceImpl.generateTemplate(jasperReport, new TestPfDto("JASPER_MEDVEDEVQEQ"));
        assertNotNull(jasperPrint);

        try {
            JasperExportManager.exportReportToPdfFile(jasperPrint, "invoice.pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}