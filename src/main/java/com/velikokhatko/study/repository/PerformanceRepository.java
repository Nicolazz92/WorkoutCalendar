package com.velikokhatko.study.repository;

import com.velikokhatko.study.model.Performance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends CrudRepository<Performance, Long> {
}
