package com.csv.uploader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csv.uploader.csvhelper.CsvHelper;
import com.csv.uploader.entity.InsuranceSample;
import com.csv.uploader.repository.InsuranceRepository;

@Service
public class InsuranceUploaderService {

	@Autowired
	private InsuranceRepository insuranceRepository;

	@Autowired
	private CsvHelper csvHelper;

	public void saveAllCsvdata(MultipartFile file) {
		try {
			List<InsuranceSample> insuranceSampleList = this.csvHelper.csvToInsuranceSample(file);
			System.out.println(insuranceSampleList);
			this.insuranceRepository.saveAll(insuranceSampleList);
		} catch (Exception e) {
			throw new RuntimeException("Failed to save data to database, try again " + e.getMessage());
		}
	}

	public List<InsuranceSample> getAllInsurancedata() {
		try {
			List<InsuranceSample> getInsuranceData = this.insuranceRepository.findAll();
			return getInsuranceData;
		} catch (Exception e) {
			throw new RuntimeException("Somthing went wrong while featching the records " + e.getMessage());
		}
	}
}
