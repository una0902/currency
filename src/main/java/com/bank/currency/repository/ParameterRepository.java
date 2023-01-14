package com.bank.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.currency.bean.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, String>{
	
}
