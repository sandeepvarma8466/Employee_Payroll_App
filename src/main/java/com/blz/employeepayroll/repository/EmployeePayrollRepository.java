package com.blz.employeepayroll.repository;

import com.blz.employeepayroll.model.EmployeePayrollModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeePayrollRepository extends JpaRepository<EmployeePayrollModel,Long> {
    Optional<EmployeePayrollModel> findByemailId(String emailId);
}
