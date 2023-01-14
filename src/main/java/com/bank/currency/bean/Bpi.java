package com.bank.currency.bean;

import com.google.gson.annotations.SerializedName;

public class Bpi{
	//@JsonProperty(value = "USD")
	@SerializedName("USD")
	private Usd usd;
	//@JsonProperty(value = "GBP")
	@SerializedName("GBP")
	private Gbp gbp;
	//@JsonProperty(value = "EUR")
	@SerializedName("EUR")
	private Eur eur;

	public Bpi(){
		super();
	}
	public Usd getUsd(){
		return usd;
	}
	public void setUsd(Usd usd){
		this.usd=usd;
	}
	public Gbp getGbp(){
		return gbp;
	}
	public void setGbp(Gbp gbp){
		this.gbp=gbp;
	}
	public Eur getEur(){
		return eur;
	}
	public void setEur(Eur eur){
		this.eur=eur;
	}
	@Override
	public String toString(){
		return "Bpi [usd="+usd+", gbp="+gbp+", eur="+eur+"]";
	}
}