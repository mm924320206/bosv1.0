package com.boxugu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.boxugu.domain.base.Courier;

public interface CourierService {

	void save(Courier model);

	Page<Courier> findPageData(Specification<Courier> specification, Pageable pageable);

	void delBatch(String[] idArray);

	List<Courier> findNoAssociation(Specification<Courier> specification);

}
