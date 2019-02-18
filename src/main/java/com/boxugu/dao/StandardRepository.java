package com.boxugu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.boxugu.domain.base.Standard;

public interface StandardRepository extends JpaRepository<Standard, Integer>,JpaSpecificationExecutor<Standard> {

	
}
