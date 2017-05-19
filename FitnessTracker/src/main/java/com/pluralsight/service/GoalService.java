package com.pluralsight.service;

import java.util.List;

import com.pluralsight.model.Goal;
import com.pluralsight.model.GoalReport;

public interface GoalService {
	
	public Goal save(Goal goal);

	public List<Goal> findAllGoals();

	public List<GoalReport> findAllGoalReports();

}
