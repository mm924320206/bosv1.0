package com.boxugu.service.impl;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boxugu.dao.StandardRepository;
import com.boxugu.domain.base.Standard;
import com.boxugu.service.StandardService;
@Service("StandardService")
@Transactional
public class StandardServiceImpl implements StandardService {

	@Autowired
	@Qualifier("standardRepository")
	private StandardRepository standardRepository;
	
	
	@Override
	public void save(Standard standard) {
		// TODO Auto-generated method stub
		standardRepository.saveAndFlush(standard);
	}
	@Override
	public Page<Standard> findPageData(Pageable pageable) {
		// TODO Auto-generated method stub
		return standardRepository.findAll(pageable);
	}

}
