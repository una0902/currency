package com.bank.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bank.currency.service.CurrencyService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CurrencyApplicationTests {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private WebApplicationContext context;
	@Resource
	private CurrencyService currencyService;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	@Test
	void queryAPI() throws Exception {
		assertEquals(200,callApi("/callcoindesk"));
	}
	
	@Test
	void querySql() throws Exception {
		assertEquals(200,callApi("/queryData"));
	}

	@Test
	void insertSql() throws Exception { //新增
		assertEquals(200,callApi("/insertData"));
	}
	
	@Test
	void updateSql() throws Exception { //修改
		assertEquals(200,callApi("/updateData"));
	}
	
	@Test
	void deleteSql() throws Exception { //刪除
		assertEquals(200,callApi("/deleteData"));
	}

	public int callApi(String url) throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(url)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = result.getResponse().getStatus();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println("Http status code : " + status);
		return status;
	}
}
