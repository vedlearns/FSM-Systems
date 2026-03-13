package com.hackerrank.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SortedProducts {
	


	private String BarCode;

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}


	public SortedProducts(String a) {
		BarCode=a;
	}


}