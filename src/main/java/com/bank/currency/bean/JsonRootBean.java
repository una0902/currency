package com.bank.currency.bean;

public class JsonRootBean{
	private Time time;
	private Object disclaimer;
	private String chartName;
	private Bpi bpi;
	
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Object getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(Object disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public Bpi getBpi() {
		return bpi;
	}
	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}
	public JsonRootBean(){
		super();
	}
	@Override
	public String toString(){
		return "[time="+time+", disclaimer="+disclaimer+", chartName="+chartName+", bpi="+bpi+"]";
	}
}