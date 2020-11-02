package com.dillon.restapi.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestRun {
    
    @Id
    private long testRunId;

    private long testId;

    private String plant;

    private String area;

    private Set<TestResult> results = new HashSet<>();
}
