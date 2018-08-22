package com.tinyjira.repository;

import org.springframework.data.repository.CrudRepository;

import com.tinyjira.dto.Assignee;

public interface AssigneeRespository extends CrudRepository<Assignee, Long> {

}
