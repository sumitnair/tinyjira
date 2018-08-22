package com.tinyjira.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinyjira.dto.Assignee;
import com.tinyjira.exception.NotFoundException;
import com.tinyjira.repository.AssigneeRespository;
import com.tinyjira.util.Constant;


@Service
@Transactional
public class AssigneeService {
	
	@Autowired 
	private AssigneeRespository assigneeRespository;
	
	/**
	 * retrieves assignees from database
	 * @return List
	 */
	public List<Assignee> findAllAssignees() throws NotFoundException {
		List<Assignee> assignees = (List<Assignee>) assigneeRespository.findAll();
		if(assignees.isEmpty()){
			throw new NotFoundException(Constant.NO_ASSIGNESS);
		}else{
			return assignees;
		}
	}
	
	/**
	 * Inserts an assignee into database
	 * @param story
	 */
	public void createAssignee(Assignee assignee) {
		assigneeRespository.save(assignee);
	}
	
	/**
	 * Deletes an assignee from database
	 * @param assigneeId
	 */
	public void deleteAssignee(Long assigneeId) {		
		assigneeRespository.delete(assigneeId);
	}
}
