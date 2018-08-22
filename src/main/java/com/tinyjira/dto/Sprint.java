package com.tinyjira.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sprint")
public class Sprint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sprintNo;
	
	@OneToMany
	@JoinTable(name = "sprint_stories",joinColumns = {
			@JoinColumn(name = "sprint_no")}, inverseJoinColumns = {
			@JoinColumn(name = "story_id") })
	private List<Story> plannedStories;
	
	public Integer getSprintNo() {
		return sprintNo;
	}
	public void setSprintNo(Integer sprintNo) {
		this.sprintNo = sprintNo;
	}
	public List<Story> getPlannedStories() {
		if(plannedStories == null) {
			plannedStories = new ArrayList<>();
		}
		return plannedStories;
	}
	public void setPlannedStories(List<Story> plannedStories) {
		this.plannedStories = plannedStories;
	}
	@Override
	public String toString() {
		return "Sprint [sprintNo=" + sprintNo + ", plannedStories=" + plannedStories + "]";
	}
	
	
}
