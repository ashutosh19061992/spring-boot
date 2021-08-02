package com.csv.uploader.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import com.csv.uploader.csvhelper.AlertMessage;
import com.csv.uploader.csvhelper.CsvHelper;
import com.csv.uploader.entity.InsuranceSample;
import com.csv.uploader.service.InsuranceUploaderService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Controller
public class UploadController {

	@Autowired
	private InsuranceUploaderService insuranceUploaderService;

	@Autowired
	private CsvHelper csvHelper;

	@GetMapping("/homePage")
	public String loadHomePage() {
		return "index";
	}

	@PostMapping("/upload-csv-data")
	public RedirectView uploadCsvdata(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttrs,
			HttpServletRequest forCurrentRequest) throws IOException {

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(forCurrentRequest.getContextPath() + "/homePage");
		if (csvHelper.hasValidCsvFormat(file)) {
			try {
				this.insuranceUploaderService.saveAllCsvdata(file);
				redirectAttrs.addFlashAttribute("message", new AlertMessage("Data Uploaded successfully.", "success"));
				return redirectView;
			} catch (Exception e) {
				redirectAttrs.addFlashAttribute("message",
						new AlertMessage("Could not upload the file: " + file.getOriginalFilename() + "!", "danger"));
				return redirectView;
			}
		}

		redirectAttrs.addFlashAttribute("message", new AlertMessage("Something Went Wrong.", "danger"));
		return redirectView;
	}

	@RequestMapping(value = "/downloadCSV", method = RequestMethod.GET)
	public void downloadCSV(HttpServletResponse forCurrentResponse, RedirectAttributes redirectAttrs) {
		try {
			String filename = "insurance.csv";
			forCurrentResponse.setContentType("text/csv");
			forCurrentResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
			List<InsuranceSample> allInsurancedata = this.insuranceUploaderService.getAllInsurancedata();
			if (allInsurancedata.size() > 0) {
				StatefulBeanToCsv<InsuranceSample> beanToCsv = new StatefulBeanToCsvBuilder<InsuranceSample>(
						forCurrentResponse.getWriter()).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
								.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false).build();

				beanToCsv.write(allInsurancedata);
			}
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
			e.printStackTrace();
		}
	}
}
