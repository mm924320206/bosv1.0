package com.boxugu.service.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		courierRepository.saveAndFlush(model);
	}

	@Override
	public Page<Courier> findPageData(Specification<Courier> specification, Pageable pageable) {
		// TODO Auto-generated method stub
		return courierRepository.findAll(specification,pageable);
	}

	@Override
	public void delBatch(String[] idArray) {
		for (String idsr : idArray) {
			Integer idInteger=Integer.parseInt(idsr);
			courierRepository.updateDelTag(idInteger);
		}
		
	}

	@Override
	public List<Courier> findNoAssociation(Specification<Courier> specification) {
		// TODO Auto-generated method stub
		return courierRepository.findAll(specification);
	}

}
