package com.dillon.restapi.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Test {

    @Id
    private long testId;

    private String testName;

    private String bug;

    private Set<TestRun> testRuns = new HashSet<>();
    
}
