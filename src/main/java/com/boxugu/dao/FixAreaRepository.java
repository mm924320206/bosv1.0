package com.boxugu.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.boxugu.domain.base.FixedArea;

public interface FixAreaRepository extends JpaRepository<FixedArea, String>, JpaSpecificationExecutor<FixedArea> {

	

}
