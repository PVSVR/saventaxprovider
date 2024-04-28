package com.imageinnovative.employee.saventaxprovider.controller.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imageinnovative.employee.saventaxprovider.controller.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
