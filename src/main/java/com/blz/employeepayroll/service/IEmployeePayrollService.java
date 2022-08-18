package com.blz.employeepayroll.service;

import com.blz.employeepayroll.dto.EmployeePayrollDTO;
import com.blz.employeepayroll.model.EmployeePayrollModel;
import com.blz.employeepayroll.util.ResponseUtil;

import java.util.List;

public interface IEmployeePayrollService {
    EmployeePayrollModel addData(EmployeePayrollDTO employeePayrollDTO, Long departmentId);

    EmployeePayrollModel updateById(Long id, EmployeePayrollDTO employeePayrollDTO, String token, Long departmentId);

    List<EmployeePayrollModel> getAllData(String token);

    EmployeePayrollModel deleteData(Long id, String token);

    ResponseUtil login(String emailId, String password);
}
