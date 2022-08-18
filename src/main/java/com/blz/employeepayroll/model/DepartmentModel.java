package com.blz.employeepayroll.model;

import com.blz.employeepayroll.dto.DepartmentDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
public class DepartmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;
    private String departmentDesc;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public DepartmentModel(DepartmentDTO departmentDTO) {
        this.departmentName=departmentDTO.getDepartmentName();
        this.departmentDesc=departmentDTO.getDepartmentDesc();
    }
}
