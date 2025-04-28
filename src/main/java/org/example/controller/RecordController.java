package org.example.controller;

import org.example.dto.RecordDto;
import org.example.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service) {

        this.service = service;
    }

    @GetMapping("/{code}")
    public ResponseEntity<RecordDto> getByCode(@PathVariable String code) {
        RecordDto record = service.getByCode(code);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            service.uploadCsv(file);
            return ResponseEntity.ok("File uploaded successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllRecords() {
        service.deleteAllRecords();
        return ResponseEntity.ok("All records deleted");
    }

    @GetMapping
    public ResponseEntity<List<RecordDto>> getAllRecords() {
        return ResponseEntity.ok(service.getAllRecords());
    }
}
