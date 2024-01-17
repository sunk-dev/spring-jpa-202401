package com.study.jpa.chap04_relation.repository;

import com.study.jpa.chap04_relation.entity.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class DepartmentRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Test
    @DisplayName("특정부서를 조회하면 해당 부서원들도 함께 조회됨")
    void findDeptTest () {
        //given
        Long id=1L;
        //when
        Department department = departmentRepository.findById(id).orElseThrow();


        //then
        System.out.println("\n\n\n");
        System.out.println("department = " + department);
        System.out.println("department = " + department.getEmployeeList());
        System.out.println("\n\n\n");
    }


}