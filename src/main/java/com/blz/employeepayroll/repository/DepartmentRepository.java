package com.blz.employeepayroll.repository;

import com.blz.employeepayroll.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentModel,Long> {
}
