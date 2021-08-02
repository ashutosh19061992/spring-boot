package com.csv.uploader.csvhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import com.csv.uploader.entity.InsuranceSample;
import com.opencsv.bean.CsvToBeanBuilder;

@Configuration
public class CsvHelper {

	private static String[] fileType = new String[] { "text/csv", "application/vnd.ms-excel" };
	private static List<String> FILE_FORMAT = Arrays.stream(fileType).collect(Collectors.toList());

	public List<InsuranceSample> csvToInsuranceSample(MultipartFile file) {
		try {
			InputStream inputStream = file.getInputStream();
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

			List<InsuranceSample> insurancebean = new CsvToBeanBuilder<InsuranceSample>(fileReader)
					.withType(InsuranceSample.class).build().parse();
			return insurancebean;
		} catch (IOException e) {
			throw new RuntimeException("Failed to parse the data from CSV to InsuranceSample Bean" + e.getMessage());
		}
	}

	public boolean hasValidCsvFormat(MultipartFile file) {
		if (FILE_FORMAT.contains(file.getContentType())) {
			return true;
		}
		return false;
	}
}
