package org.example;

import org.example.dto.RecordDto;
import org.example.entity.Record;
import org.example.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @GetMapping("/{code}")
    public ResponseEntity<RecordDto> getByCode(@PathVariable String code) {
        Optional<RecordDto> record = service.getByCode(code);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            service.uploadCsv(file);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllRecords() {
        service.deleteAllRecords();
        return ResponseEntity.ok("All records deleted");
    }
}
