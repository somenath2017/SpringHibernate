/*package com.pluralsight.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pluralsight.model.Exercise;

@Repository
public class ExcerciseRepositoryImpl implements ExcerciseRepository {

	@PersistenceContext
	private EntityManager em;
	
	
	public Exercise save(Exercise ex) {
		// TODO Auto-generated method stub
		em.persist(ex);
		em.flush();
		return ex;
	}

}
*/
package com.pluralsight.repository;
public class ExcerciseRepositoryImpl
{
	// It was there for using JPA. But with Spring- data JPA we don't need to use this class. 
}