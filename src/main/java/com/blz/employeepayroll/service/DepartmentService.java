package com.blz.employeepayroll.service;

import com.blz.employeepayroll.dto.DepartmentDTO;
import com.blz.employeepayroll.exception.EmployeeNotFoundException;
import com.blz.employeepayroll.model.DepartmentModel;
import com.blz.employeepayroll.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public DepartmentModel addDepartment(DepartmentDTO departmentDTO) {
        DepartmentModel departmentModel = new DepartmentModel(departmentDTO);
        departmentModel.setCreatedTime(LocalDateTime.now());
        departmentRepository.save(departmentModel);
        return departmentModel;
    }

    @Override
    public DepartmentModel updateDepartment(DepartmentDTO departmentDTO, Long id) {
        Optional<DepartmentModel> isIdPresent = departmentRepository.findById(id);
        if (isIdPresent.isPresent()) {
            isIdPresent.get().setDepartmentName(departmentDTO.getDepartmentName());
            isIdPresent.get().setDepartmentDesc(departmentDTO.getDepartmentDesc());
            isIdPresent.get().setUpdatedTime(LocalDateTime.now());
            departmentRepository.save(isIdPresent.get());
            return isIdPresent.get();
        }
        throw new EmployeeNotFoundException("Department is not found with this ID", 500);
    }

    @Override
    public List<DepartmentModel> getAllDepartments() {
        List<DepartmentModel> isDepartment = departmentRepository.findAll();
        if (isDepartment.size() > 0) {
            return isDepartment;
        }
        throw new EmployeeNotFoundException("No Departments Found", 500);
    }

    @Override
    public DepartmentModel deleteDepartment(Long id) {
        Optional<DepartmentModel> isIdPresent = departmentRepository.findById(id);
        if (isIdPresent.isPresent()) {
            departmentRepository.delete(isIdPresent.get());
            return isIdPresent.get();
        }
        throw new EmployeeNotFoundException("Department is not found", 500);
    }
}
