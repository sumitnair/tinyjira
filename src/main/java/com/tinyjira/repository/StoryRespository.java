package com.tinyjira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tinyjira.dto.Story;

public interface StoryRespository extends CrudRepository<Story, Long> {
	
	@Query("SELECT SUM(s.storyPoint) from Story s WHERE s.storyPoint is not NULL")
	public Integer getTotalSizeOfStories();

	public List<Story> findByStatusAndStoryPointNotNullOrderByStoryPointDesc(Story.Status status);
	
}
