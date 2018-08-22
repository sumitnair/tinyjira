package com.tinyjira.repository;

import org.springframework.data.repository.CrudRepository;

import com.tinyjira.dto.Bug;

public interface BugRespository extends CrudRepository<Bug, Long> {
}
