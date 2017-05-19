package com.pluralsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Exercise;

@Repository("exerciseRepository")
public interface ExcerciseRepository extends JpaRepository<Exercise,Long>{
	
	//Exercise save(Exercise ex);

}
