package com.boxugu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boxugu.dao.CourierRepository;
import com.boxugu.dao.FixAreaRepository;
import com.boxugu.dao.LeaderRepository;
import com.boxugu.dao.TakeTimeRepository;
import com.boxugu.domain.base.Courier;
import com.boxugu.domain.base.FixAreaLeader;
import com.boxugu.domain.base.FixedArea;
import com.boxugu.domain.base.TakeTime;
import com.boxugu.service.FixAreaService;

@Service("FixAreaService")
@Transactional
public class FixAreaServiceImpl implements FixAreaService {

	@Autowired
	@Qualifier("fixAreaRepository")
	private FixAreaRepository fixAreaRepository;
	
	@Autowired
	@Qualifier("leaderRepository")
	private LeaderRepository leaderRepository;
	
	@Autowired
	@Qualifier("takeTimeRepository")
	private TakeTimeRepository takeTimeRepository;
	
	@Autowired
	@Qualifier("courierRepository")
	private CourierRepository courierRepository;
	
	
	@Override
	public void save(FixedArea model) {
		fixAreaRepository.saveAndFlush(model);
	}
	@Override
	public Page<FixedArea> findPageData(Specification<FixedArea> specification, Pageable pageable) {
		// TODO Auto-generated method stub
		return fixAreaRepository.findAll(specification, pageable);
	}
	@Override
	public void associationCourierToFixedArea(FixedArea model, Integer courierId, Integer takeTimeId) {
		// TODO Auto-generated method stub
		FixedArea fixedArea=fixAreaRepository.findOne(model.getId());
		Courier courier=courierRepository.findOne(courierId);
		TakeTime takeTime=takeTimeRepository.findOne(takeTimeId);
		fixedArea.getCouriers().add(courier);
		courier.setTakeTime(takeTime);
		
	}

	
	/*
	 * @Autowired
	 * 
	 * @Qualifier("fixarearepository") private FixAreaRepository fixarearepository;
	 * 
	 */

}
