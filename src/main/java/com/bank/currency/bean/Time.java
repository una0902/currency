package com.bank.currency.bean;

import lombok.Data;

@Data
public class Time{
    private String updated;
    private String updatedISO;
    private String updateduk;
    
    public String getUpdated(){
		return updated;
	}

	public void setUpdated(String updated){
		this.updated=updated;
	}

	public String getUpdatedISO(){
		return updatedISO;
	}

	public void setUpdatedISO(String updatedISO){
		this.updatedISO=updatedISO;
	}

	public String getUpdateduk(){
		return updateduk;
	}

	public void setUpdateduk(String updateduk){
		this.updateduk=updateduk;
	}

	public Time() {
        super();
    }
}