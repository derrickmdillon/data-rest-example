package com.dillon.restapi.repository;

import com.dillon.restapi.model.TestRun;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "test-runs")
public interface TestRunRepository extends PagingAndSortingRepository<TestRun, Long> {
}
