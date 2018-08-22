package com.tinyjira.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinyjira.dto.Assignee;
import com.tinyjira.domain.Container;
import com.tinyjira.dto.Sprint;
import com.tinyjira.dto.Story;
import com.tinyjira.dto.Story.Status;
import com.tinyjira.exception.NotFoundException;
import com.tinyjira.repository.AssigneeRespository;
import com.tinyjira.repository.SprintRepository;
import com.tinyjira.repository.StoryRespository;
import com.tinyjira.util.Constant;

@Service
@Transactional
public class PlanningService {

	@Autowired 
	private StoryRespository storyRespository;

	@Autowired
	private AssigneeRespository assigneeRespository; 
	
	@Autowired
	private SprintRepository sprintRepository; 

	/**
	 *  main method to implement planning of stories.
	 */
	public List<Sprint> planStoriesForSprint() {

		List<Story> estimatedStories = fetchEstimatedStoriesFromBacklog();	
		Integer totalSizeOfStories = storyRespository.getTotalSizeOfStories();


		List<Assignee> developerList = fetchAssigneesAvailable();		
		Collections.shuffle(developerList);

		Map<Integer, Container> capacityMap = getStoryListsBasedCapacity(estimatedStories,totalSizeOfStories);		

		List<Sprint> sprintPlan = planSprint(developerList, new LinkedList<>(capacityMap.values()));
		
		if(!sprintPlan.isEmpty()) {
			sprintPlan = (List<Sprint>) sprintRepository.save(sprintPlan);
		}
		
		return sprintPlan;
	}

	/**
	 *  assigns all the stories in capacity list to all the developer and map them into sprints.
	 * @param developerList
	 * @param capacityList
	 * @return list of sprints
	 */
	
	private List<Sprint> planSprint(List<Assignee> developerList, LinkedList<Container> capacityList) {
		
		List<Sprint> sprintList = new ArrayList<>();

		while(!capacityList.isEmpty()) {

			Sprint sprint = new Sprint();
			assignStorytoDeveloperForSprint(developerList, capacityList, sprint);
			if(!sprint.getPlannedStories().isEmpty()) {
				storyRespository.save(sprint.getPlannedStories());		
				sprintList.add(sprint);
			}			
		}
		
		return sprintList;
	}


	
	/** 
	 * assigns stories to each developer for single sprint.
	 * @param developerList
	 * @param capacityList
	 * @param sprint
	 */
	private void assignStorytoDeveloperForSprint(List<Assignee> developerList, LinkedList<Container> capacityList,
			Sprint sprint) {
		for (Assignee developer : developerList) {

			if(capacityList.isEmpty()) {
				break;
			}

			Container container = capacityList.removeFirst();		
			container.getStories().stream().forEach(s->s.setAssignee(developer));								

			sprint.getPlannedStories().addAll(container.getStories());
		}
	}



	/**
	 * @param estimatedStories
	 * @param totalSizeOfStories
	 * @return Map containing list of  Individual capacities 
	 */
	private Map<Integer, Container> getStoryListsBasedCapacity(List<Story> estimatedStories, Integer totalSizeOfStories) {

		int minimumBuckets = totalSizeOfStories / Constant.INDIVIDUAL_CAPACITY;
		if (totalSizeOfStories % Constant.INDIVIDUAL_CAPACITY != 0) {
			minimumBuckets++;
		}

		Map<Integer, Container> map = new HashMap<>();
		for (int i = 1; i<= minimumBuckets; i++) {
			map.put(i, new Container());
		}

		Iterator<Story> iterator = estimatedStories.iterator();

		while (iterator.hasNext()) {
			Story story =  iterator.next();

			for (int j = 1; j <= minimumBuckets; j++) {
				Container container = map.get(j);
				Integer storyCountAfterAdding = container.getTotalStoryPoint() + story.getStoryPoint();
				if (storyCountAfterAdding <= Constant.INDIVIDUAL_CAPACITY) {
					container.getStories().add(story);
					container.setTotalStoryPoint(storyCountAfterAdding);					
					// assuming map element got updated
					break;
					// break from inner loop to fetch next story
				}
			}
			totalSizeOfStories = totalSizeOfStories - story.getStoryPoint();
			iterator.remove();
		}

		if (!estimatedStories.isEmpty()) {
			Map<Integer, Container> remainingList = new HashMap<>();
			remainingList = getStoryListsBasedCapacity(estimatedStories, totalSizeOfStories);

			for (Integer key : remainingList.keySet()) {
				Container container = remainingList.get(key);
				map.put(++minimumBuckets, container);
			}
		}

		return map;
	}
	
	
	/**
	 * Query for assignees from database.
	 * @return Assignees
	 */
	private List<Assignee> fetchAssigneesAvailable() {
		List<Assignee> developerList =  (List<Assignee>) assigneeRespository.findAll();		
		if(developerList.isEmpty()) {
			throw new NotFoundException(Constant.NO_ASSIGNESS);
		}
		return developerList;
	}

	/**
	 * Query for estimated stories from database.
	 * @return Stories
	 */
	
	private List<Story> fetchEstimatedStoriesFromBacklog() {
		/* fetch all estimated stories in descending order of story point*/
		List<Story> estimatedStories = storyRespository.findByStatusAndStoryPointNotNullOrderByStoryPointDesc(Status.ESTIMATED);		
		if(estimatedStories.isEmpty()) {
			throw new NotFoundException(Constant.NO_ESTIMATED_STORIES);
		}
		return estimatedStories;
	}

	/**
	 *  method to re plan sprints.
	 */
	
	public List<Sprint> replanStoriesForSprint() {
		
		List<Story> estimatedStories = fetchEstimatedStoriesFromBacklog();
		
		estimatedStories.stream()
		.filter(story-> null != story.getAssignee())
		.forEach(story -> story.setAssignee(null));
		
		storyRespository.save(estimatedStories);		
		sprintRepository.deleteAll();
		
		return planStoriesForSprint();
				
	}
}
