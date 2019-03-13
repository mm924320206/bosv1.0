package com.boxugu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.boxugu.domain.base.TakeTime;

public interface TakeTimeRepository extends JpaRepository<TakeTime, Integer>, JpaSpecificationExecutor<TakeTime> {

}
