package com.bank.currency.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "detail", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "chartname", "code" }) })
@IdClass(RelationshipPK.class)
public class Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	@Id
	@Column(name = "chartname", nullable = false, length = 100)
	private String chartname;
	@Id
	@Column(name = "code", nullable = false, length = 10)
	private String code;
	@Column(name = "symbol", nullable = false, length = 60)
	private String symbol;
	@Column(name = "rate", nullable = false, length = 17)
	private String rate;
	@Column(name = "description", nullable = false, length = 600)
	private String description;
	@Column(name = "ratefloat", nullable = false, length = 17)
	private String ratefloat;

	public Detail() {
		super();
	}

	public Detail(Integer id, String chartname, String code, String symbol, String rate, String description,
			String ratefloat) {
		super();
		this.id = id;
		this.chartname = chartname;
		this.code = code;
		this.symbol = symbol;
		this.rate = rate;
		this.description = description;
		this.ratefloat = ratefloat;
	}

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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRatefloat() {
		return ratefloat;
	}

	public void setRatefloat(String ratefloat) {
		this.ratefloat = ratefloat;
	}

	@Override
	public String toString() {
		return "Detail [id=" + id + ", chartname=" + chartname + ", code=" + code + ", symbol=" + symbol + ", rate="
				+ rate + ", description=" + description + ", ratefloat=" + ratefloat + "]";
	}
}
