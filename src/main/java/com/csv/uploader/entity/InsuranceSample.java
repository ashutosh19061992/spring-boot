package com.csv.uploader.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

@Entity
public class InsuranceSample {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CsvBindByName(column = "policyId")
	@CsvBindByPosition(position = 0)
	@Column(nullable = false)
	private String policyId;
	
	@CsvBindByName(column = "stateCode")
	@CsvBindByPosition(position = 1)
	@Column(nullable = false)
	private String stateCode;
	
	@CsvBindByName(column = "country")
	@CsvBindByPosition(position = 2)
	@Column(nullable = false)
	private String country;

	public InsuranceSample(Long id, String policyId, String stateCode, String country) {
		super();
		this.id = id;
		this.policyId = policyId;
		this.stateCode = stateCode;
		this.country = country;
	}

	public InsuranceSample() {
		super();
	}

	public String getPolicyId() {
		return policyId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "InsuranceSample [policyId=" + policyId + ", stateCode=" + stateCode + ", country=" + country + "]";
	}
}
