package com.boxugu.service;

import java.util.List;

import com.boxugu.domain.base.FixAreaLeader;

public interface LeaderService {

	List<FixAreaLeader> findAll();

	void save(FixAreaLeader model);

	/* List<FixAreaLeader> findByName(String string); */
}
