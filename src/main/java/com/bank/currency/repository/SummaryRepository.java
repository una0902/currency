package com.bank.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.currency.bean.Summary;
@Repository
public interface SummaryRepository extends JpaRepository<Summary, Long>{

}
