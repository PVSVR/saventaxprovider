package com.imageinnovative.employee.saventaxprovider.service;

import com.imageinnovative.employee.saventaxprovider.controller.entity.Employee;
import com.imageinnovative.employee.saventaxprovider.uientity.EmployeTaxDTO;
import com.imageinnovative.employee.saventaxprovider.uientity.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(Employee employeeDTO);

    EmployeTaxDTO getTaxDetails(String employeeCode);
}
