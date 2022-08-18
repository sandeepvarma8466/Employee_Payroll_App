package com.blz.employeepayroll.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class EmployeePayrollDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,8}$", message = "Name Should Start with capital letter and min of 3 letter")
    private String name;
    @Min(value = 500, message = "Min wage should be more than 500")
    private long salary;
    @Pattern(regexp = "male | female", message = "Gender needs to be male or female")
    private String gender;
    private LocalDate startDate;
    @NotBlank(message = "Note Cannot be empty ")
    private String note;
    @NotNull(message = "CompanyName Can't be Empty ")
    @Pattern(regexp = "[A-Z]{1}[a-zA-Z]{2,}",message = "Company name start with capital letter and minimum 3 letters")
    private String companyName;
    @NotNull(message = "department cannot be empty")
    private String department;
    @NotNull(message = "email should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid EmailId")
    private String emailId;
    @NotNull(message = "password cannot be null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%!]).{8,}$", message = "Invalid Password")
    private String password;
}
