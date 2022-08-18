package com.blz.employeepayroll.controller;

import com.blz.employeepayroll.dto.EmployeePayrollDTO;
import com.blz.employeepayroll.model.EmployeePayrollModel;
import com.blz.employeepayroll.service.IEmployeePayrollService;
import com.blz.employeepayroll.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employeepayroll")
public class EmployeePayrollController {
    @Autowired
    IEmployeePayrollService payrollService;

    @PostMapping("/adddata")
    public EmployeePayrollModel addData(@Valid @RequestBody EmployeePayrollDTO employeePayrollDTO, @RequestParam Long departmentId) {
        return payrollService.addData(employeePayrollDTO,departmentId);
    }

    @PutMapping("updateby/{id}")
    public EmployeePayrollModel updateById(@PathVariable("id") Long id, @RequestBody EmployeePayrollDTO employeePayrollDTO,
                                           @RequestHeader String token, @RequestParam Long departmentId) {
        return payrollService.updateById(id,employeePayrollDTO,token,departmentId);
    }

    @GetMapping("/getdata")
    public List<EmployeePayrollModel> getData(@RequestHeader String token) {
        return payrollService.getAllData(token);
    }

    @DeleteMapping("deeleteby/{id}")
    public EmployeePayrollModel deleteData(@PathVariable("id") Long id,@RequestHeader String token) {
        return payrollService.deleteData(id,token);
    }

    @PostMapping("/login")
    public ResponseUtil login(@RequestParam String emailId, @RequestParam String password) {
        return payrollService.login(emailId,password);
    }
}
