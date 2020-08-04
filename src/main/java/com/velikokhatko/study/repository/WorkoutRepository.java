package com.velikokhatko.study.repository;

import com.velikokhatko.study.model.Workout;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long>, JpaSpecificationExecutor<Workout> {
}
