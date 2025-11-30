package com.codingshuttle.om.module2.repository;

import com.codingshuttle.om.module2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department , Long> {
}
