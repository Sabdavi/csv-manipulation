package org.example.service;

import org.example.dto.RecordDto;
import org.example.entity.Record;
import org.example.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecordService {
    RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Optional<RecordDto> getByCode(String code) {
        return recordRepository.findByCode(code).map(Record::toRecordDto);
    }
}
