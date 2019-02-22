package com.boxugu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.boxugu.domain.base.Area;

public interface AreaRepository extends JpaRepository<Area, String>,JpaSpecificationExecutor<Area>{

}
