package com.boxugu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boxugu.dao.CourierRepository;
import com.boxugu.domain.base.Courier;
import com.boxugu.service.CourierService;
@Service("CourierService")
@Transactional
public class CourierServiceImpl implements CourierService {

	@Autowired
	@Qualifier("courierRepository")
	private CourierRepository courierRepository;
	@Override
	public void save(Courier model) {
		// TODO Auto-generated method stub
		courierRepository.save(model);
	}

	@Override
	public Page<Courier> findPageData(Specification<Courier> specification, Pageable pageable) {
		// TODO Auto-generated method stub
		return courierRepository.findAll(specification,pageable);
	}

}
