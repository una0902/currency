package com.bank.currency.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "parameter", uniqueConstraints = { @UniqueConstraint(columnNames = { "chartname" }) })
public class Parameter implements Serializable {
	public Parameter(String chartname, String chtname) {
		super();
		this.chartname = chartname;
		this.chtname = chtname;
	}

	public Parameter() {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "chartname", nullable = false, length = 100)
	private String chartname;
	@Column(name = "chtname", nullable = false, length = 100)
	private String chtname;

	public String getChartname() {
		return chartname;
	}

	public void setChartname(String chartname) {
		this.chartname = chartname;
	}

	public String getChtname() {
		return chtname;
	}

	public void setChtname(String chtname) {
		this.chtname = chtname;
	}

	@Override
	public String toString() {
		return "Parameter [chartname=" + chartname + ", chtname=" + chtname + "]";
	}
}
