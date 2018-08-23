/**
 * 
 */
package com.tinyjira.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "assignee")
public class Assignee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long assigneeId;
	
	@NotNull
	@Size(min=2, message = "Name should be atleast two character.")
	private String assigneeName;
	
	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	@Override
	public String toString() {
		return "Assignee [assigneeId=" + assigneeId + ", assigneeName=" + assigneeName + "]";
	}
	
}
