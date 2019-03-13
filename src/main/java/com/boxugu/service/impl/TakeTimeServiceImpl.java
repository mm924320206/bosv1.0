package com.boxugu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boxugu.dao.TakeTimeRepository;
import com.boxugu.domain.base.TakeTime;
import com.boxugu.service.TakeTimeService;
@Service("TakeTimeService")
@Transactional
public class TakeTimeServiceImpl implements TakeTimeService {

	@Autowired
	@Qualifier("takeTimeRepository")
	private TakeTimeRepository takeTimeRepository;
	
	
	@Override
	public void save(TakeTime model) {
		// TODO Auto-generated method stub
		takeTimeRepository.saveAndFlush(model);
	}


	@Override
	public Page<TakeTime> findPageData(Pageable pageable) {
		// TODO Auto-generated method stub
		return takeTimeRepository.findAll(pageable);
	}


	@Override
	public List<TakeTime> findAll() {
		// TODO Auto-generated method stub
		return takeTimeRepository.findAll();
	}

}
