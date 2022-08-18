package com.blz.employeepayroll.model;

import com.blz.employeepayroll.dto.EmployeePayrollDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_payroll")
@Data
@NoArgsConstructor
public class EmployeePayrollModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;
    private String name;
    private long salary;
    private String gender;
    private LocalDate startDate;
    private String note;
    private String companyName;
    @OneToOne
    private DepartmentModel department;
    private String emailId;
    private String password;

    public EmployeePayrollModel(EmployeePayrollDTO employeePayrollDTO) {
        this.name = employeePayrollDTO.getName();
        this.salary = employeePayrollDTO.getSalary();
        this.startDate = employeePayrollDTO.getStartDate();
        this.gender = employeePayrollDTO.getGender();
        this.note = employeePayrollDTO.getNote();
        this.companyName = employeePayrollDTO.getCompanyName();
        this.emailId = employeePayrollDTO.getEmailId();
        this.password = employeePayrollDTO.getPassword();
    }
}
