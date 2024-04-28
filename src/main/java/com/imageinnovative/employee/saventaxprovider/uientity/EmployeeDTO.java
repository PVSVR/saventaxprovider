package com.imageinnovative.employee.saventaxprovider.uientity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Carries data for UI representation.
 * 
 * Includes validation. Scenarios to check mandatory
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Employee ID cannot be Empty")
    private String employeeId;

    @NotBlank(message = "First Name cannot be Empty")
    private String firstName;

    @NotBlank(message = "Last Name cannot be Empty")
    private String lastName;

    @NotNull(message = "Date of Joining cannot be Empty")
    @Past(message = "Date of Joining can be in the past date")
    private LocalDate doj;

    @DecimalMin(value = "0.1", message = "Salary should be greater than 0.0")
    @NotNull (message = "Salary can not be blank")
    private Double salary;

    @NotEmpty(message = "At least one phone number is required")
    private List<String> phoneNumbers = new ArrayList<>();

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be Empty")
    private String emailAddress;

}
