package com.bank.currency.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "summary", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Summary implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	@Column(name = "time", nullable = false, length = 30)
	private String time;
	@Column(name = "disclaimer", nullable = true, length = 600)
	private String disclaimer;
	@Column(name = "chartname", nullable = false, length = 100)
	private String chartname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartname() {
		return chartname;
	}

	public void setChartname(String chartname) {
		this.chartname = chartname;
	}
	
	public Summary() {
		super();
	}

	public Summary(Integer id, String time, String disclaimer, String chartname) {
		super();
		this.id = id;
		this.time = time;
		this.disclaimer = disclaimer;
		this.chartname = chartname;
	}

	@Override
	public String toString() {
		return "Summary [id=" + id + ", time=" + time + ", disclaimer=" + disclaimer + ", chartname=" + chartname + "]";
	}
}
