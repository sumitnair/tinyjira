package com.tinyjira.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinyjira.dto.Story;
import com.tinyjira.exception.NotFoundException;
import com.tinyjira.repository.StoryRespository;
import com.tinyjira.util.BeanUtil;
import com.tinyjira.util.Constant;

@Service
@Transactional
public class StoryService {

	@Autowired 
	private StoryRespository storyRespository;

	/**
	 * retrieves stories from database
	 * @return List
	 */
	public List<Story> fetchAllStories() {
		List<Story> stories = (List<Story>) storyRespository.findAll();
		if(stories.isEmpty()){
			throw new NotFoundException(Constant.NO_STORIES);
		}else{
			return stories;
		}
	}

	/**
	 * Inserts a story into database
	 * @param story
	 */
	public void createStory(Story story) {
		storyRespository.save(story);
	}
	
	/**
	 * Updates a story in database.
	 * @param updatedStory
	 */
	public void updateStoryDetails(Story updatedStory) {
		Optional<Story> existingStory = Optional.ofNullable(storyRespository.findOne(updatedStory.getStoryId()));
		if(existingStory.isPresent()) {
			Story story = existingStory.get();
			BeanUtils.copyProperties(updatedStory, story, BeanUtil.getIgnoredPropertyNames(updatedStory));
			storyRespository.save(story);
		}else{
			throw new NotFoundException("Story to be updated not found with id:" + updatedStory.getStoryId());
		}
	}
	
	
	/**
	 * retrieves story from database
	 * @param storyId
	 * @return Story
	 */
	public Story getStoryDetails(Long storyId) {		
		Optional<Story> story = Optional.ofNullable(storyRespository.findOne(storyId));

		if(story.isPresent()){
			return story.get();
		}else{
			throw new NotFoundException("Story not found with id:" + storyId);
		}
	}
}
