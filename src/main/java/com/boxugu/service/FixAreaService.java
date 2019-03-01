package com.boxugu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.boxugu.domain.base.FixedArea;


public interface FixAreaService {

	void save(FixedArea model);

	Page<FixedArea> findPageData(Specification<FixedArea> specification, Pageable pageable);

	

	

}
