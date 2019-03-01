package com.boxugu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boxugu.dao.LeaderRepository;
import com.boxugu.domain.base.FixAreaLeader;
import com.boxugu.service.LeaderService;

@Service("LeaderService")
@Transactional
public class LeaderServiceImpl implements LeaderService {

	@Autowired
	@Qualifier("leaderRepository")
	private LeaderRepository leaderRepository;
	
	@Override
	public List<FixAreaLeader> findAll() {
		// TODO Auto-generated method stub
		return leaderRepository.findAll();
	}

	@Override
	public void save(FixAreaLeader model) {
		leaderRepository.saveAndFlush(model);
		
	}

	/*
	 * @Override public List<FixAreaLeader> findByName(String name) { // TODO
	 * Auto-generated method stub return null; }
	 */

}
