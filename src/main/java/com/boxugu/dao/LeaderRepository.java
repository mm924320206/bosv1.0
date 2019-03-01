package com.boxugu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.boxugu.domain.base.FixAreaLeader;

public interface LeaderRepository extends JpaRepository<FixAreaLeader,Integer>{

}
