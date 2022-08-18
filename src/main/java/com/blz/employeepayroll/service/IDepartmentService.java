package com.blz.employeepayroll.service;

import com.blz.employeepayroll.dto.DepartmentDTO;
import com.blz.employeepayroll.model.DepartmentModel;

import java.util.List;

public interface IDepartmentService {
    DepartmentModel addDepartment(DepartmentDTO departmentDTO);

    DepartmentModel updateDepartment(DepartmentDTO departmentDTO, Long id);

    List<DepartmentModel> getAllDepartments();

    DepartmentModel deleteDepartment(Long id);
}
