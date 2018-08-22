package com.tinyjira.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinyjira.dto.Bug;
import com.tinyjira.service.BugService;

/**
 * Controller to handle HTTP request for creation/deletion/updation and listing of Bugs.
 */
@RestController
@RequestMapping("/bug")
public class BugController {
	
	@Autowired
	private BugService bugService;
		
	/**
	 * Handler method to Retrieve list of bugs available.
	 */
	@GetMapping
	public ResponseEntity<List<Bug>> getAllBugs () {

		HttpStatus status = HttpStatus.OK;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		List<Bug> providerDetails = bugService.fetchAllBugs();
		
		return new ResponseEntity<>(providerDetails, httpHeaders, status);
	}
	
	/**
	 * Handler method to Retrieve a bug based on bugId.
	 */
	@GetMapping(value="/{id}",produces = "application/json")
	public Bug getBug(@PathVariable("id") Long bugId) {
		return bugService.getBugDetails(bugId);		
	}
	
	/**
	 * Handler method to Create a bug.
	 * @param Bug
	 */
	@PostMapping(consumes = "application/json")
	public void create (@Valid @RequestBody Bug bug) {
		bugService.createBug(bug);
	}
	
	/**
	 * Handler method to update a bug.
	 * @param Bug
	 */
	@PutMapping(consumes = "application/json")
	public void updateBug(@RequestBody Bug bug) {		
		bugService.updateBugDetails(bug);		
	}
}
