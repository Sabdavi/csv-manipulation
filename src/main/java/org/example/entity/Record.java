package org.example.entity;

import org.example.dto.RecordDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "source")
    private String source;
    @Column(name = "codeListCode")
    private String codeListCode;
    @Column(name= "code", unique = true)
    private String code;
    @Column(name = "displayValue")
    private String displayValue;
    @Column(name = "longDescription")
    private String longDescription;
    @Column(name = "fromDate")
    private LocalDate fromDate;
    @Column(name = "toDate")
    private LocalDate toDate;
    @Column(name = "sortingPriority")
    private Integer sortingPriority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCodeListCode() {
        return codeListCode;
    }

    public void setCodeListCode(String codeListCode) {
        this.codeListCode = codeListCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Integer getSortingPriority() {
        return sortingPriority;
    }

    public void setSortingPriority(Integer sortingPriority) {
        this.sortingPriority = sortingPriority;
    }

    public RecordDto toRecordDto() {
        RecordDto recordDto = new RecordDto();
        recordDto.setSource(this.source);
        recordDto.setCodeListCode(this.codeListCode);
        recordDto.setCode(this.code);
        recordDto.setDisplayValue(this.displayValue);
        recordDto.setLongDescription(this.longDescription);
        recordDto.setFromDate(this.fromDate);
        recordDto.setToDate(this.toDate);
        recordDto.setSortingPriority(this.sortingPriority);
        return recordDto;
    }
}
