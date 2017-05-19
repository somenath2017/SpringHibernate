/*package com.pluralsight.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.pluralsight.model.Goal;
import com.pluralsight.model.GoalReport;

@Repository("goalRepository")
public class GoalRepositoryImpl implements GoalRepository {
	
	@PersistenceContext
	private EntityManager em;

	public Goal save(Goal goal) {
		if(goal.getId() == null)
		{
			em.persist(goal);
			em.flush();
		}else{
			goal = em.merge(goal);
		}
		
		return goal;
	}

	@SuppressWarnings("unchecked")
	public List<Goal> findAllGoals() {
		// TODO Auto-generated method stub
		//Query query = em.createQuery("Select distinct g from Goal g join fetch g.excercises");
		Query query= em.createNamedQuery(Goal.FIND_GOALS,Goal.class);
		List<Goal> goals = query.getResultList();
		
		return goals;
	}

	public List<GoalReport> findAllGoalReports() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("Select new com.pluralsight.model.GoalReport(g.minutes,e.minutes,e.activity)" + 
									"from Goal g,Exercise e where g.id = e.goal.id");
		TypedQuery<GoalReport> query = em.createNamedQuery(Goal.FIND_GOAL_REPORTS,GoalReport.class);
		List<GoalReport> goalReports = query.getResultList();
		
		return goalReports;
	}

}
*/

package com.pluralsight.repository;
public class GoalRepositoryImpl
{
	//Commented this class because using Spring data JPA
}