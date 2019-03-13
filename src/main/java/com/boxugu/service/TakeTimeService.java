package com.boxugu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.boxugu.domain.base.TakeTime;

public interface TakeTimeService {

	void save(TakeTime model);

	Page<TakeTime> findPageData(Pageable pageable);

	List<TakeTime> findAll();

}
