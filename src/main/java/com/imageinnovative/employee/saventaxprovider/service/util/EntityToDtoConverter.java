package com.imageinnovative.employee.saventaxprovider.service.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import com.imageinnovative.employee.saventaxprovider.controller.entity.Employee;
import com.imageinnovative.employee.saventaxprovider.uientity.EmployeTaxDTO;
import com.imageinnovative.employee.saventaxprovider.uientity.EmployeeDTO;

public class EntityToDtoConverter {
	
	public static EmployeeDTO employeeToEmployeeDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeId(employee.getEmployeeId());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setDoj(employee.getDoj());
		employeeDTO.setSalary(employee.getSalary());
		employeeDTO.setPhoneNumbers(employee.getPhoneNumbers());
		
		employeeDTO.setEmailAddress(employee.getEmailAddress());
		
		return employeeDTO;
	}

	//tax calculation logic resides here
	public static EmployeTaxDTO employeeToEmployeeTaxDTO(Employee employee) {
		EmployeTaxDTO employeTaxDTO = new EmployeTaxDTO();
		
		LocalDate today = LocalDate.now();
		LocalDate yearToCalulateTax = LocalDate.now();
		
		if(LocalDate.now().getMonthValue() > 3) {
			yearToCalulateTax = LocalDate.of(LocalDate.now().getYear() +1, 3, 31);
		} else {
			yearToCalulateTax = LocalDate.of(LocalDate.now().getYear(), 3, 31);
		}
		
		// Calculate the period between the two dates
        Period period = Period.between(yearToCalulateTax, employee.getDoj());
        
        // Extract the months and days from the period
        int months = period.getMonths();
        int days = period.getDays();
        
        //Leap year and all days considered
        if(days >= 365) {
        	employeTaxDTO.setYearlySalary(employee.getSalary() * 12.0);
        } else {

            LocalDate endOfMonth = employee.getDoj().withDayOfMonth(employee.getDoj().lengthOfMonth());
            Period period1 = Period.between(endOfMonth, employee.getDoj());
            
            employeTaxDTO.setYearlySalary((employee.getSalary() * months) + (((employee.getSalary())/30) * period1.getDays()));
        }
        
        employeTaxDTO.setTaxAmount(calculateTax(employeTaxDTO.getYearlySalary()));
        employeTaxDTO.setCessAmount(calculateCess(employeTaxDTO.getYearlySalary()));
        
		return employeTaxDTO;
	}

    private static double calculateTax(double totalSalary) {
        if(totalSalary <= 250000) {
            return 0;
        } else if(totalSalary <= 500000) {
            return ((totalSalary - 250000) * (0.05));
        } else if(totalSalary <= 1000000) {
            return 12500 + ((totalSalary - 500000) * (0.10));
        } else {
            return 62500+ ((totalSalary - 1000000) * (0.20));
        }
    }
    private static double calculateCess(double totalSalary) {
        return totalSalary > 2500000 ? (totalSalary - 2500000) * (0.02) : 0;
    }
}
