/**
 * 
 */
package com.tinyjira.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Sumit_Nair
 *
 */

@MappedSuperclass
public class Issue {
	

	@NotNull
	@Size(min=5,message="title should have atleast 5 characters")
	private String title;
	@Size(min=15,message="description should have atleast 15 characters")
	private String description;
	@Column(updatable = false)
	private Date creationDate;	
	
	public Issue() {
		this.creationDate = new Date();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return "Issue [title=" + title + ", description=" + description + ", creationDate=" + creationDate + "]";
	}

	
}
