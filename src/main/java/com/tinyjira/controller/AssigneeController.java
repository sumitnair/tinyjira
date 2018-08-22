package com.tinyjira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinyjira.dto.Assignee;
import com.tinyjira.service.AssigneeService;


/**
 * Controller to handle HTTP request for creation/deletion and listing of Assignees.
 */
@RestController
@RequestMapping("/assignees")
public class AssigneeController {
	
	@Autowired
	private AssigneeService assigneeService;
		
	/**
	 * Handler method to Retrieve list of assignees.
	 */
	@GetMapping
	public ResponseEntity<List<Assignee>> findAllAssignees () {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		List<Assignee> providerDetails = assigneeService.findAllAssignees();
		
		return new ResponseEntity<>(providerDetails, httpHeaders, HttpStatus.OK);
	}
	
	/**
	 * Handler method to Create an assignee.
	 * @param assignee
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public void create (@RequestBody Assignee assignee) {
		assigneeService.createAssignee(assignee);
	}
	
	
	/**
	 * Handler method to Delete an assignee based on assigneeId.
	 * @param assigneeId
	 */
	@DeleteMapping(value="/{assigneeId}")
	public void deleteAssignee(@PathVariable("assigneeId") Long assigneeId) {		
		assigneeService.deleteAssignee(assigneeId);		
	}
}
