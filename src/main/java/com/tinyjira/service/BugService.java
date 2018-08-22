package com.tinyjira.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinyjira.dto.Bug;
import com.tinyjira.exception.NotFoundException;
import com.tinyjira.repository.BugRespository;
import com.tinyjira.util.BeanUtil;
import com.tinyjira.util.Constant;

@Service
@Transactional
public class BugService {

	@Autowired 
	private BugRespository bugRespository;

	/**
	 * retrieves bugs from database
	 * @return List
	 */
	public List<Bug> fetchAllBugs() {
		List<Bug> bugs = (List<Bug>) bugRespository.findAll();
		if(bugs.isEmpty()){
			throw new NotFoundException(Constant.NO_BUGS);
		}else{
			return bugs;
		}
	}

	/**
	 * Inserts a bug into database
	 * @param bug
	 */
	public void createBug(Bug bug) {
		bugRespository.save(bug);
	}


	/**
	 * Updates a bug in database.
	 * @param updatedStory
	 */
	public void updateBugDetails(Bug updatedBug) {
		Optional<Bug> existingBug = Optional.ofNullable(bugRespository.findOne(updatedBug.getBugId()));
		if(existingBug.isPresent()) {
			Bug bug = existingBug.get();
			BeanUtils.copyProperties(updatedBug, bug, BeanUtil.getIgnoredPropertyNames(updatedBug));
			bugRespository.save(bug);
		}else{
			throw new NotFoundException("Bug to be updated not found with id:" + updatedBug.getBugId());
		}
	}
	
	/**
	 * retrieves bug from database
	 * @param bugId
	 * @return {@link Bug}
	 */
	public Bug getBugDetails(Long bugId) {		
		Optional<Bug> bug = Optional.ofNullable(bugRespository.findOne(bugId));

		if(bug.isPresent()) {
			return bug.get();
		}else{
			throw new NotFoundException("Bug not found with id:" + bugId);
		}
	}
}
