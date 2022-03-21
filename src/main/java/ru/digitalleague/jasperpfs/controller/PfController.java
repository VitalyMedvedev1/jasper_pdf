package ru.digitalleague.jasperpfs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.jasperpfs.dto.TestPfDto;
import ru.digitalleague.jasperpfs.service.JasperReportService;
import ru.digitalleague.jasperpfs.service.JasperReportServiceImpl;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test-pf")
public class PfController {
    private final JasperReportService jasperReportService;

    public PfController() {
        this.jasperReportService = new JasperReportServiceImpl();
    }

    @PostMapping("/create")
    public byte[] create(@RequestBody TestPfDto dto) {
        log.debug("Accept request with params: {}", dto);
        return jasperReportService.generate(dto);
    }
}