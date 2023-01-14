package com.bank.currency.bean;

import java.io.Serializable;

public class RelationshipPK implements Serializable {
	private static final long serialVersionUID=1L;
    private Integer id;
    private String chartname;
    private String code;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChartname() {
		return chartname;
	}

	public void setChartname(String chartname) {
		this.chartname = chartname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public RelationshipPK() {
		super();
	}
}