package com.blz.employeepayroll.service;

import com.blz.employeepayroll.dto.EmployeePayrollDTO;
import com.blz.employeepayroll.exception.EmployeeNotFoundException;
import com.blz.employeepayroll.model.DepartmentModel;
import com.blz.employeepayroll.model.EmployeePayrollModel;
import com.blz.employeepayroll.repository.DepartmentRepository;
import com.blz.employeepayroll.repository.EmployeePayrollRepository;
import com.blz.employeepayroll.util.ResponseUtil;
import com.blz.employeepayroll.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{
    @Autowired
    EmployeePayrollRepository payrollRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public EmployeePayrollModel addData(EmployeePayrollDTO employeePayrollDTO, Long departmentId) {
        Optional<DepartmentModel> isDepartment = departmentRepository.findById(departmentId);
        if (isDepartment.isPresent()) {
            EmployeePayrollModel payrollModel = new EmployeePayrollModel(employeePayrollDTO);
            payrollModel.setDepartment(isDepartment.get());
            payrollRepository.save(payrollModel);
            String body = "Employee added successfully with employeeId id is " + payrollModel.getEmployeeId();
            String suvject = "Employee Added Successfully";
            mailService.send(payrollModel.getEmailId(), body, suvject);
            return payrollModel;
        }
        throw new EmployeeNotFoundException("Department is not found",500);
    }

    @Override
    public EmployeePayrollModel updateById(Long id, EmployeePayrollDTO employeePayrollDTO, String token, Long departmentId) {
        Long decodeToken = tokenUtil.decodeToken(token);
        Optional<DepartmentModel> isDepartment = departmentRepository.findById(departmentId);
        if (isDepartment.isPresent()) {
            Optional<EmployeePayrollModel> isEmployeePresent = payrollRepository.findById(decodeToken);
            if (isEmployeePresent.isPresent()) {
                Optional<EmployeePayrollModel> optional = payrollRepository.findById(id);
                if (isEmployeePresent.isPresent()) {
                    isEmployeePresent.get().setName(employeePayrollDTO.getName());
                    isEmployeePresent.get().setSalary(employeePayrollDTO.getSalary());
                    isEmployeePresent.get().setGender(employeePayrollDTO.getGender());
                    isEmployeePresent.get().setStartDate(LocalDate.now());
                    isEmployeePresent.get().setNote(employeePayrollDTO.getNote());
                    isEmployeePresent.get().setCompanyName(employeePayrollDTO.getCompanyName());
                    isEmployeePresent.get().setEmailId(employeePayrollDTO.getEmailId());
                    isEmployeePresent.get().setPassword(employeePayrollDTO.getPassword());
                    payrollRepository.save(isEmployeePresent.get());
                    String body = "Employee updated successfully with Employee id is " + isEmployeePresent.get().getEmployeeId();
                    String suvject = "Employee updated Successfully";
                    mailService.send(isEmployeePresent.get().getEmailId(), body, suvject);
                    return isEmployeePresent.get();
                }
                throw new EmployeeNotFoundException("EmployeeId Not Found", 500);
            }
            throw new EmployeeNotFoundException("Employee token not found", 500);
        }
        throw new EmployeeNotFoundException("Department is not found with this ID",500);
    }

    @Override
    public List<EmployeePayrollModel> getAllData(String token) {
        Long decodeToken = tokenUtil.decodeToken(token);
        Optional<EmployeePayrollModel> isEmployeePresent = payrollRepository.findById(decodeToken);
        if (isEmployeePresent.isPresent()) {
            List<EmployeePayrollModel> isDataPresent = payrollRepository.findAll();
            if (isDataPresent.size() > 0) {
                return isDataPresent;
            }
            throw new EmployeeNotFoundException("No Data Found", 500);
        }
        throw new EmployeeNotFoundException("given token not found", 500);
    }

    @Override
    public EmployeePayrollModel deleteData(Long id, String token) {
        Long decodeToken = tokenUtil.decodeToken(token);
        Optional<EmployeePayrollModel> optional = payrollRepository.findById(decodeToken);
        if (optional.isPresent()) {
            Optional<EmployeePayrollModel> isEmployeePresent = payrollRepository.findById(id);
            if (isEmployeePresent.isPresent()) {
                payrollRepository.delete(isEmployeePresent.get());
                String body = "Employee deleted successfully with contact id is " + isEmployeePresent.get().getEmployeeId();
                String suvject = "Contact deleted Successfully";
                mailService.send(isEmployeePresent.get().getEmailId(), body, suvject);
                return isEmployeePresent.get();
            }
            throw new EmployeeNotFoundException("EmployeeId Not Found", 500);
        }
        throw new EmployeeNotFoundException("EmployeeId Not Found", 500);
    }

    @Override
    public ResponseUtil login(String emailId, String password) {
        Optional<EmployeePayrollModel> optional = payrollRepository.findByemailId(emailId);
        if (optional.isPresent()) {
            if (optional.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(optional.get().getEmployeeId());
                return new ResponseUtil(200, "Login successful", token);
            }
            throw new EmployeeNotFoundException("Employee not found", 500);
        }
        throw new EmployeeNotFoundException("Employee not found", 500);
    }
}
