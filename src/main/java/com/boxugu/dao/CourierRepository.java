package com.boxugu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.boxugu.domain.base.Courier;


public interface CourierRepository extends JpaRepository<Courier, Integer>,JpaSpecificationExecutor<Courier>{

}
