package com.tinyjira.domain;

import java.util.ArrayList;
import java.util.List;

import com.tinyjira.dto.Story;

public class Container {
	
	public List<Story> getStories() {
		return stories;
	}
	public void setStories(List<Story> stories) {
		this.stories = stories;
	}
	public Integer getTotalStoryPoint() {
		return totalStoryPoint;
	}
	public void setTotalStoryPoint(Integer totalStoryPoint) {
		this.totalStoryPoint = totalStoryPoint;
	}
	private List<Story> stories;
	private Integer totalStoryPoint;
	
	public Container() {
		super();
		this.stories = new ArrayList<>();;
		this.totalStoryPoint = 0;
	}	
	
}
