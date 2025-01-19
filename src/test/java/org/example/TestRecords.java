package org.example;

import org.example.dto.RecordDto;
import org.example.entity.Record;
import org.example.repository.RecordRepository;
import org.example.service.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TestRecords {
    @Mock
    private RecordRepository repository;

    @InjectMocks
    private RecordService service;

    public TestRecords() throws IOException {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRecords() {
        List<Record> records = new ArrayList<>();
        Record record = new Record();
        record.setCode("271636001");
        records.add(record);

        when(repository.findAll()).thenReturn(records);

        List<RecordDto> result = service.getAllRecords();

        assertEquals(1, result.size());
        assertEquals("271636001", result.get(0).getCode());
    }

    @Test
    void testGetByCode() {
        Record record = new Record();
        record.setCode("271636001");

        when(repository.findByCode("271636001")).thenReturn(Optional.of(record));

        RecordDto result = service.getByCode("271636001");

        assertNotNull(result);
        assertEquals("271636001", result.getCode());
    }

    @Test
    void testUploadCsv() throws Exception {
        ClassPathResource resource = new ClassPathResource("exercise.csv");
        InputStream inputStream = new FileInputStream(resource.getFile());
        MultipartFile multipartFile = mock(MultipartFile.class);

        when(multipartFile.getInputStream()).thenReturn(inputStream);

        service.uploadCsv(multipartFile);
        verify(repository, times(1)).saveAll(argThat((List<Record> records) -> records.size() == 18));


    }
}
