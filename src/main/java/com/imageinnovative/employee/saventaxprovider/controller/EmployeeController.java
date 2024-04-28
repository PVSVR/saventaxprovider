package com.imageinnovative.employee.saventaxprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imageinnovative.employee.saventaxprovider.controller.entity.Employee;
import com.imageinnovative.employee.saventaxprovider.service.EmployeeService;
import com.imageinnovative.employee.saventaxprovider.service.impl.EmployeeServiceImpl;
import com.imageinnovative.employee.saventaxprovider.uientity.EmployeTaxDTO;
import com.imageinnovative.employee.saventaxprovider.uientity.EmployeeDTO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

/**
 * Controller class to provide service.
 * 
 * @author Srinivasa Rao
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService = new EmployeeServiceImpl();

	@GetMapping("/hello")
	@Operation
	public String helloWorld() {
		return "hello world!";
	}

	@PostMapping
	public ResponseEntity<EmployeeDTO> saveEmployee(
			@RequestBody @Valid Employee employee) {

		return new ResponseEntity<EmployeeDTO>(
				employeeService.saveEmployee(employee), HttpStatus.ACCEPTED);
	}

	@GetMapping("/taxDetails/{employeeCode}")
	public ResponseEntity<EmployeTaxDTO> getTaxDetails(
			@PathVariable(name = "employeeCode") @NotBlank(message = "Please provide"
					+ " valid employee code") String employeeCode) {
		return new ResponseEntity<>(employeeService.getTaxDetails(employeeCode), HttpStatus.OK);
	}
}
