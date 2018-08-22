package com.tinyjira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tinyjira.dto.Sprint;
import com.tinyjira.service.SprintService;

/**
 * Controller to handle HTTP request for sprints.
 */
@RestController
public class SprintController {
	
	@Autowired
	private SprintService sprintService;
	/**
	 * Handler method to Retrieve sprint details
	 */
	@GetMapping(value="/sprint/{id}",produces = "application/json")
	public Sprint getSprintDetails(@PathVariable("id") Integer sprintNo) {
		return sprintService.getSprintDetails(sprintNo);		
	}
	
	/**
	 * Handler method to Retrieve entire sprints details
	 */
	@GetMapping(value="/sprints",produces = "application/json")
	public List<Sprint> getAllSprintDetails() {
		return sprintService.getAllSprintDetails();	
	}
}
