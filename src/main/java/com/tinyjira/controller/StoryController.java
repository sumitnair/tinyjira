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

import com.tinyjira.dto.Story;
import com.tinyjira.service.StoryService;

/**
 * Controller to handle HTTP request for creation/deletion/updation and listing of Stories.
 */
@RestController
@RequestMapping("/story")
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	/**
	 * Handler method to Retrieve list of stories available.
	 */
	@GetMapping
	public ResponseEntity<List<Story>> getAllStories () {

		HttpStatus status = HttpStatus.OK;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		List<Story> providerDetails = storyService.fetchAllStories();
		
		return new ResponseEntity<>(providerDetails, httpHeaders, status);
	}
	
	/**
	 * Handler method to Retrieve a story based on storyId.
	 */
	@GetMapping(value="/{id}")
	public Story getStory(@PathVariable("id") Long storyId) {
		return storyService.getStoryDetails(storyId);
	}
	
	
	/**
	 * Handler method to Create a story.
	 * @param Bug
	 */
	@PostMapping(consumes = "application/json")
	public void create (@Valid @RequestBody Story story) {
		storyService.createStory(story);
	}
	
	/**
	 * Handler method to update a story.
	 * @param Bug
	 */
	@PutMapping(consumes = "application/json")
	public void updateStory(@RequestBody Story story) {		
		storyService.updateStoryDetails(story);		
	}
}
