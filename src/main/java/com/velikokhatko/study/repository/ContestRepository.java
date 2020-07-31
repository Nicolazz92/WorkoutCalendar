package com.velikokhatko.study.repository;

import com.velikokhatko.study.model.Contest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends CrudRepository<Contest, Long> {
}
