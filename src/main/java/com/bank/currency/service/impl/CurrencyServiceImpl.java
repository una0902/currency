package com.bank.currency.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bank.currency.bean.Detail;
import com.bank.currency.bean.Parameter;
import com.bank.currency.bean.QDetail;
import com.bank.currency.bean.QParameter;
import com.bank.currency.bean.QSummary;
import com.bank.currency.bean.Summary;
import com.bank.currency.repository.DetailRepository;
import com.bank.currency.repository.ParameterRepository;
import com.bank.currency.repository.SummaryRepository;
import com.bank.currency.service.CurrencyService;
import com.querydsl.jpa.impl.JPAQueryFactory;
@Service
public class CurrencyServiceImpl implements CurrencyService {
	@Resource
	DetailRepository detailRepository;
	@Resource
	SummaryRepository summaryRepository;
	@Resource
	ParameterRepository parameterRepository;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Summary> fetchSummaryList() {
		return (List<Summary>) summaryRepository.findAll();
	}

	@Override
	public List<Detail> fetchDetailList() {
		return (List<Detail>) detailRepository.findAll();
	}

	@Override
	@Transactional
	public void insertSummary(Summary summary) {
		summaryRepository.save(summary);
	}

	@Override
	@Transactional
	public Long updateSummary(Summary summary) {
		QSummary qSummary = QSummary.summary;
		JPAQueryFactory jpaQuery = new JPAQueryFactory(entityManager);
		long num = jpaQuery.update(qSummary).set(qSummary.time, summary.getTime())
				.set(qSummary.disclaimer, summary.getDisclaimer()).set(qSummary.chartname, summary.getChartname())
				.where(qSummary.id.eq(summary.getId())).execute();
		return num;
	}

	@Override
	@Transactional
	public Long updateDetail(Detail detail) {
		QDetail qDetail = QDetail.detail;
		JPAQueryFactory jpaQuery = new JPAQueryFactory(entityManager);
		long num = jpaQuery.update(qDetail).set(qDetail.chartname, detail.getChartname())
				.set(qDetail.code, detail.getCode()).set(qDetail.symbol, detail.getSymbol())
				.set(qDetail.rate, detail.getRate()).set(qDetail.ratefloat, detail.getRatefloat())
				.where(qDetail.id.eq(detail.getId())).execute();
		return num;
	}

	@Override
	@Transactional
	public Long deleteSummary(int id) {
		JPAQueryFactory jpaQuery = new JPAQueryFactory(entityManager);
		return jpaQuery.delete(QSummary.summary).where(QSummary.summary.id.eq(id)).execute();
	}

	@Override
	@Transactional
	public Long deleteDetail(int id) {
		JPAQueryFactory jpaQuery = new JPAQueryFactory(entityManager);
		return jpaQuery.delete(QDetail.detail).where(QDetail.detail.id.eq(id)).execute();
	}

	@Override
	public Parameter findByChartname(String chartname) {
		JPAQueryFactory jpaQuery = new JPAQueryFactory(entityManager);
		return jpaQuery.selectFrom(QParameter.parameter).where(QParameter.parameter.chartname.eq(chartname))
				.fetchFirst();
	}
}
