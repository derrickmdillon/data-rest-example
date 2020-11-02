package com.dillon.restapi.repository;

import com.dillon.restapi.model.Test;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestRepository extends PagingAndSortingRepository<Test, Long> {
}
