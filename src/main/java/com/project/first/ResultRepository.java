package com.project.first;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long>{
	
	Result findById(Long id);
	Result save (Result result);

}
