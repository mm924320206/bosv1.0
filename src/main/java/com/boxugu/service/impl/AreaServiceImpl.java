package com.boxugu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boxugu.dao.AreaRepository;
import com.boxugu.domain.base.Area;
import com.boxugu.service.AreaService;

@Service("AreaService")
@Transactional
public class AreaServiceImpl implements AreaService {

	@Autowired
	@Qualifier("areaRepository")
	private AreaRepository areaRepository;

	@Override
	public void saveFromBatchImport(List<Area> areas) {
		// TODO Auto-generated method stub
		areaRepository.save(areas);
	}

	@Override
	public Page<Area> findPageData(Specification<Area> specification, Pageable pageable) {
		// TODO Auto-generated method stub
		return areaRepository.findAll(specification,pageable);
	}

	
}
