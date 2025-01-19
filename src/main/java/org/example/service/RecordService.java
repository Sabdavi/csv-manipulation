package org.example.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.dto.RecordDto;
import org.example.entity.Record;
import org.example.exception.CsvProcessingException;
import org.example.exception.RecordNotFoundException;
import org.example.repository.RecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordService {
    RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public RecordDto getByCode(String code) {
        return recordRepository.findByCode(code).map(Record::toRecordDto).orElseThrow(()  -> new RecordNotFoundException(code));
    }

    @Transactional
    public void uploadCsv(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            List<Record> records = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                Record record = new Record();
                record.setSource(csvRecord.get("source"));
                record.setCodeListCode(csvRecord.get("codeListCode"));
                record.setCode(csvRecord.get("code"));
                record.setDisplayValue(csvRecord.get("displayValue"));
                record.setLongDescription(csvRecord.get("longDescription"));
                record.setFromDate(csvRecord.get("fromDate"));
                record.setToDate(csvRecord.get("toDate"));
                if(!csvRecord.get("sortingPriority").isEmpty()) {
                    record.setSortingPriority(Integer.parseInt(csvRecord.get("sortingPriority")));
                }
                records.add(record);
            }
            recordRepository.saveAll(records);
        } catch (Exception e) {
            throw new CsvProcessingException("Error processing the uploaded CSV file", e);
        }
    }

    public void deleteAllRecords() {
        recordRepository.deleteAll();
    }

    public List<RecordDto> getAllRecords() {
        return recordRepository.findAll().stream().map(Record::toRecordDto).collect(Collectors.toList());
    }
}
