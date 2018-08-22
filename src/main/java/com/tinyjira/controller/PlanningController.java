/**
 * 
 */
package com.tinyjira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinyjira.dto.Sprint;
import com.tinyjira.service.PlanningService;

/**
 * Controller to handle HTTP request for planning stories.
 */
@RestController
public class PlanningController {
	
	@Autowired
	private PlanningService planningService;
	/**
	 * Handler method to plan stories
	 * @return 
	 */
	@GetMapping(value="/plan/stories",produces = "application/json")
	public ResponseEntity<List<Sprint>> planStories() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		List<Sprint> sprintPlan = planningService.planStoriesForSprint();
		return new ResponseEntity<>(sprintPlan, httpHeaders, HttpStatus.OK);

	}
	
	/**
	 * Handler method to re-plan stories if developers count changes
	 * @return 
	 */
	@GetMapping(value="/replan/stories",produces = "application/json")
	public ResponseEntity<List<Sprint>> replanStories() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		List<Sprint> sprintPlan = planningService.replanStoriesForSprint();
		return new ResponseEntity<>(sprintPlan, httpHeaders, HttpStatus.OK);

	}
	
	
	
	/**
	 * Handler method to plan bugs
	 */	
	@GetMapping(value="/plan/bugs")
	public void planBugs() {
		//TODO
	}
}
