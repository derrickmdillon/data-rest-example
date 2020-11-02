package com.dillon.restapi.repository;

import com.dillon.restapi.model.TestResult;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "test-results")
public interface TestResultRepository extends PagingAndSortingRepository<TestResult, Long> {
}
