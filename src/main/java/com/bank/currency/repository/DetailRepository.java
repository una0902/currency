package com.bank.currency.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.currency.bean.Detail;
import com.bank.currency.bean.RelationshipPK;
@Repository
public interface DetailRepository extends CrudRepository<Detail, RelationshipPK>{
	
}
