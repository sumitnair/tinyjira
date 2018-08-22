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

@Entity
@Table(name="bug")
public class Bug extends Issue {

	public enum Priority { CRITICAL,MAJOR,MINOR }
	public enum Status { NEW,VERIFIED,RESOLVED }
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long bugId;

	@ManyToOne
	@JoinColumn(name = "assignee_id", nullable = true)
	private Assignee assignee;

	@Enumerated(EnumType.STRING)
	private Priority priority;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	public Bug() {
		super();
		this.status = Status.NEW;
	}
	public Long getBugId() {
		return bugId;
	}

	public void setBugId(Long bugId) {
		this.bugId = bugId;
	}

	public Assignee getAssignee() {
		return assignee;
	}

	public void setAssignee(Assignee assignee) {
		this.assignee = assignee;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Bug [bugId=" + bugId + ", assignee=" + assignee + ", priority=" + priority + ", status=" + status + "]";
	}
	
}
