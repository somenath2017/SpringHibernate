package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;


@Entity
@Table(name="goals")
/*@NamedQueries({@NamedQuery(name=Goal.FIND_GOAL_REPORTS,query="Select new com.pluralsight.model.GoalReport(g.minutes,e.minutes,e.activity)" + 
									"from Goal g,Exercise e where g.id = e.goal.id"),
			   @NamedQuery(name=Goal.FIND_GOALS,query="Select distinct g from Goal g join fetch g.excercises")
			  })*/
public class Goal {

	
	public static final String FIND_GOAL_REPORTS = "findGoalReports";
	public static final String FIND_GOALS ="findGoals";
	
	@Id
	@GeneratedValue
	@Column(name="GOAL_ID")
	private Long id;
	
	@Range(min = 1, max = 120)
	@Column(name="MINUTES")
	private int minutes;
	
	@OneToMany(mappedBy="goal",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Exercise> excercises = new ArrayList<Exercise>();

	public List<Exercise> getExcercises() {
		return excercises;
	}

	public Long getId() {
		return id;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setExcercises(List<Exercise> excercises) {
		this.excercises = excercises;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	
}
