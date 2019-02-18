package com.boxugu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.boxugu.domain.base.Standard;

public interface StandardService {

	void save(Standard standard);

	Page<Standard> findPageData(Pageable pageable);

}
