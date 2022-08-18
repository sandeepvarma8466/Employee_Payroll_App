package com.blz.employeepayroll.controller;

import com.blz.employeepayroll.dto.DepartmentDTO;
import com.blz.employeepayroll.model.DepartmentModel;
import com.blz.employeepayroll.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    IDepartmentService departmentService;

    @PostMapping("/addDepartment")
    public DepartmentModel addDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.addDepartment(departmentDTO);
    }
    @PutMapping("/updateDepartment/{id}")
    public DepartmentModel updateDepartment(@Valid @RequestBody DepartmentDTO departmentDTO, @PathVariable Long id){
        return departmentService.updateDepartment(departmentDTO,id);
    }
    @GetMapping("/getAllDepartments")
    public List<DepartmentModel> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
    @DeleteMapping("/deleteDepartment/{id}")
    public DepartmentModel deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }
}
