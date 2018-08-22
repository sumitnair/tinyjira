package com.tinyjira.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tinyjira.dto.Sprint;
import com.tinyjira.exception.NotFoundException;
import com.tinyjira.repository.SprintRepository;
import com.tinyjira.util.Constant;

@Service
@Transactional
public class SprintService {
	@Autowired
	private SprintRepository sprintRepository;
	
	/**
	 *  Retrieve sprint details from database
	 */
	public Sprint getSprintDetails(Integer sprintNo) {
		Optional<Sprint> sprint = Optional.ofNullable(sprintRepository.findOne(sprintNo));		
		if(sprint.isPresent()) {
			return sprint.get();
		}else{
			throw new NotFoundException(Constant.NO_SPRINT);
		}
	}
	
	/**
	 *  Retrieve entire sprints details from database
	 */
	public List<Sprint> getAllSprintDetails() {
		List<Sprint> sprints = (List<Sprint>) sprintRepository.findAll();
		if(sprints.isEmpty()){
			throw new NotFoundException(Constant.NO_SPRINT);
		}else{
			return sprints;
		}
	}
}
