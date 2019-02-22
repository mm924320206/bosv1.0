package com.boxugu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.boxugu.domain.base.Area;

public interface AreaService {

	void saveFromBatchImport(List<Area> areas);

	Page<Area> findPageData(Specification<Area> specification, Pageable pageable);

}
