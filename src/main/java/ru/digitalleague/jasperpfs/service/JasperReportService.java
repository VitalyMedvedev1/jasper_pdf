package ru.digitalleague.jasperpfs.service;

import ru.digitalleague.jasperpfs.dto.TestPfDto;

public interface JasperReportService {
    byte[] generate(TestPfDto input);
}
