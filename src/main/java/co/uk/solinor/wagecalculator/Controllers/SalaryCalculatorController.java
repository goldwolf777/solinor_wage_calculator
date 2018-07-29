package co.uk.solinor.wagecalculator.Controllers;

import co.uk.solinor.wagecalculator.Domain.Employee;
import co.uk.solinor.wagecalculator.SalaryCalculation.SalaryCalculationService;
import co.uk.solinor.wagecalculator.Storage.StorageFileNotFoundException;
import co.uk.solinor.wagecalculator.Storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class SalaryCalculatorController {
	private final StorageService storageService;
	private SalaryCalculationService salaryCalculationService;

	@Autowired
	public SalaryCalculatorController(StorageService storageService, SalaryCalculationService salaryCalculationService) {
		this.storageService = storageService;
		this.salaryCalculationService = salaryCalculationService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {


		model.addAttribute("files", storageService.loadAll());

		return "homePage";
	}

	@PostMapping("/serveFile")
	public String serveFile(@RequestParam("file") String file, RedirectAttributes redirectAttributes) {

		List<Employee> employeeList = null;
		try {
			employeeList = salaryCalculationService.calculateSalariesFromCsv(file);
			redirectAttributes.addFlashAttribute("message",
						"Calculated Salary for " + file)
						.addFlashAttribute("employeeList", employeeList);
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("error",
						"Unable to read " + file);
		}

		return "redirect:/";
	}

	@GetMapping("/deleteCsv/{filename:.+}")
	public String deleteCsv(@PathVariable String filename, Model model) {

		if (storageService.deleteByFileName(filename)) {
			model.addAttribute("message", filename + " is removed");
		} else {
			model.addAttribute("message", filename + " unable to remove");
		}
		model.addAttribute("files", storageService.loadAll());
		return "homePage";
	}

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
	                               RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Please select a file to upload");
		} else {
			storageService.store(file);
			List<Employee> employeeList = null;
			try {
				employeeList = salaryCalculationService.calculateSalariesFromCsv(file.getOriginalFilename());
				redirectAttributes.addFlashAttribute("message",
							"Calculated Salary for " + file.getOriginalFilename())
							.addFlashAttribute("employeeList", employeeList);
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("error",
							"Unable to read  " + file.getOriginalFilename());
			}
		}

		return "redirect:/";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
