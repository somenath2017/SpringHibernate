package com.pluralsight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pluralsight.model.Goal;
import com.pluralsight.model.GoalReport;
import com.pluralsight.repository.GoalRepository;

@Service("goalService")
public class GoalServiceImpl implements GoalService {
	
	@Autowired
	private GoalRepository goalRespository;

	@Transactional
	public Goal save(Goal goal) {
		// TODO Auto-generated method stub
		return goalRespository.save(goal);
	}

	@Transactional
	public List<Goal> findAllGoals() {
		// TODO Auto-generated method stub
		return goalRespository.findAllGoals();
	}

	public List<GoalReport> findAllGoalReports() {
		// TODO Auto-generated method stub
		return goalRespository.findAllGoalReports();
	}

}
