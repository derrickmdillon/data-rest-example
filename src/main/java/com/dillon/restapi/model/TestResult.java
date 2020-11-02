package com.dillon.restapi.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestResult {
    
    @Id
    private long testResultId;

    private long testRunId;

    private String name;

    private String value;
}
