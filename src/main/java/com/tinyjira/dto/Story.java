package com.tinyjira.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="story")
public class Story extends Issue {
	


	public enum Status { NEW,ESTIMATED,COMPLETED }
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long storyId;
	
	@ManyToOne
	@JoinColumn(name = "assignee_id",nullable=true)
	private Assignee assignee;
	
	@Range(min=1,max=10,message="story point should have between 1-10")
	private Integer storyPoint;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	public Story() {
		super();
		this.status = Status.NEW;
	}

	public Long getStoryId() {
		return storyId;
	}

	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}

	public Assignee getAssignee() {
		return assignee;
	}

	public void setAssignee(Assignee assignee) {
		this.assignee = assignee;
	}

	public Integer getStoryPoint() {
		return storyPoint;
	}

	public void setStoryPoint(Integer storyPoint) {
		this.storyPoint = storyPoint;
		if(storyPoint != null) 
			this.status = Status.ESTIMATED;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
	@Override
	public String toString() {
		return "Story [storyId=" + storyId + ", assignee=" + assignee + ", storyPoint=" + storyPoint + ", status="
				+ status + "]";
	}
}
