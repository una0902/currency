package com.bank.currency.service;

import java.util.List;

import com.bank.currency.bean.Detail;
import com.bank.currency.bean.Parameter;
import com.bank.currency.bean.Summary;

public interface CurrencyService {
	// 查詢資料
	public List<Summary> fetchSummaryList();

	public List<Detail> fetchDetailList();

	// 新增資料
	public void insertSummary(Summary summary);

	// 更新資料
	public Long updateSummary(Summary summary);
	
	public Long updateDetail(Detail detail);


	// 刪除資料
	public Long deleteSummary(int id);

	public Long deleteDetail(int id);

	public Parameter findByChartname(String chartname);
}
