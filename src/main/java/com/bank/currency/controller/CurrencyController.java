package com.bank.currency.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.currency.bean.Detail;
import com.bank.currency.bean.JsonRootBean;
import com.bank.currency.bean.Parameter;
import com.bank.currency.bean.Summary;
import com.bank.currency.service.CurrencyService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CurrencyController {
	private static final String OPEN_DATA_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

	@Resource
	CurrencyService currencyService;

	/*
	 * 1. 測試呼叫查詢幣別對應表資料 API，並顯示其內容
	 */
	@PostMapping("/queryData")
	public ResponseEntity<Object> getTotalData() {
		try {
			List<Summary> lists = currencyService.fetchSummaryList();
			List<Detail> details = currencyService.fetchDetailList();
			System.out.println(lists.toString());
			return ResponseEntity.ok(lists.toString() + " , " + details.toString());
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	/*
	 * 2. 測試呼叫新增幣別對應表資料 API。
	 */
	@PostMapping("/insertData")
	public ResponseEntity<Object> insertData() {
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Summary summary = new Summary();
			summary.setTime(sdf.format(now));
			summary.setId(99);
			summary.setDisclaimer("TEST");
			summary.setChartname("Ethereum");
			currencyService.insertSummary(summary);
			return ResponseEntity.ok("INSERT Success");
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("INSERT FAIL");
		}
	}
	/*
	 * 3. 測試呼叫更新幣別對應表資料 API，並顯示其內容。
	 */
	@PostMapping("/updateData")
	public ResponseEntity<Object> updateData() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Date now = new Date();
			List<Summary> lists = currencyService.fetchSummaryList();
			if (lists.isEmpty() == false) {
				Summary summary = lists.get(0);
				summary.setTime(sdf.format(now));
				currencyService.updateSummary(summary);
			}
			return ResponseEntity.ok("UPDATE SUCCESS");
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("UPDATE FAIL");
		}
	}
	/*
	 * 4. 測試呼叫刪除幣別對應表資料 API。
	 */
	@PostMapping("/deleteData")
	public ResponseEntity<Object> deleteData() {
		try {
			currencyService.deleteSummary(99);
			currencyService.deleteDetail(99);
			return ResponseEntity.ok("DELETE SUCCESS");
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("DELETE FAIL");
		}
	}
	/*
	 * 5. 測試呼叫 coindesk API，並顯示其內容以及轉換格式。
	 */
	@PostMapping("/callcoindesk")
	public ResponseEntity<Object> getOrgListData() {
		List<String> list = new ArrayList<String>();
		JsonRootBean content = new JsonRootBean();
		try {
			StringBuilder result = new StringBuilder();
			URL url = new URL(OPEN_DATA_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				for (String line; (line = reader.readLine()) != null;) {
					result.append(line);
				}
			}
			JSONObject jsonObject = new JSONObject(result.toString());
			String prettyJson = jsonObject.toString(3);
			list.add(prettyJson);

			content = new Gson().fromJson(result.toString(), JsonRootBean.class);
			String newstr = content.getTime().getUpdated().replace(" UTC", "");
			SimpleDateFormat format1 = new SimpleDateFormat("MMM dd, yyyy KK:mm:ss", Locale.US);
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Calendar c = Calendar.getInstance();
			c.setTime(format1.parse(newstr));
			list.add(combineContent(format2.format(c.getTime()), content).toString());

		} catch (FileNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (IOException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (ParseException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(list);
	}

	public String convertChtName(String chartName) {
		Parameter param = currencyService.findByChartname(chartName);
		String chtName = param == null ? "" : param.getChtname();
		return chtName;
	}

	public StringBuilder combineContent(String time, JsonRootBean jsonRootBean) {
		// 撈資料庫對照中文，幣別，幣別中文名稱，以及匯率
		StringBuilder sb = new StringBuilder();
		sb.append(time);
		sb.append(" , ");
		sb.append(jsonRootBean.getChartName());
		sb.append(" , ");
		sb.append(convertChtName(jsonRootBean.getChartName()));
		sb.append("\n");
		sb.append(jsonRootBean.getBpi().getUsd().getCode());
		sb.append(" , ");
		sb.append(convertChtName(jsonRootBean.getBpi().getUsd().getCode()));
		sb.append(" , ");
		sb.append(jsonRootBean.getBpi().getUsd().getRate());
		sb.append("\n");
		sb.append(jsonRootBean.getBpi().getGbp().getCode());
		sb.append(" , ");
		sb.append(convertChtName(jsonRootBean.getBpi().getGbp().getCode()));
		sb.append(" , ");
		sb.append(jsonRootBean.getBpi().getGbp().getRate());
		sb.append("\n");
		sb.append(jsonRootBean.getBpi().getEur().getCode());
		sb.append(" , ");
		sb.append(convertChtName(jsonRootBean.getBpi().getEur().getCode()));
		sb.append(" , ");
		sb.append(jsonRootBean.getBpi().getEur().getRate());
		return sb;
	}
}
