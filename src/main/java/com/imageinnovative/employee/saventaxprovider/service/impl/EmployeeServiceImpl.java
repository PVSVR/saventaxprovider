package com.imageinnovative.employee.saventaxprovider.service.impl;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imageinnovative.employee.saventaxprovider.controller.entity.Employee;
import com.imageinnovative.employee.saventaxprovider.controller.repo.EmployeeRepository;
import com.imageinnovative.employee.saventaxprovider.exception.EmployeeNotFoundException;
import com.imageinnovative.employee.saventaxprovider.service.EmployeeService;
import com.imageinnovative.employee.saventaxprovider.service.util.EntityToDtoConverter;
import com.imageinnovative.employee.saventaxprovider.uientity.EmployeTaxDTO;
import com.imageinnovative.employee.saventaxprovider.uientity.EmployeeDTO;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO saveEmployee(Employee employee) {
        return EntityToDtoConverter.employeeToEmployeeDTO(employeeRepository.save(employee));
    }

    @Override
    public EmployeTaxDTO getTaxDetails(String employeeCode) {
        Employee employee =
                employeeRepository.findById(employeeCode).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with employee code: " + employeeCode));
        
        return EntityToDtoConverter.employeeToEmployeeTaxDTO(employee);
    }

    
}
