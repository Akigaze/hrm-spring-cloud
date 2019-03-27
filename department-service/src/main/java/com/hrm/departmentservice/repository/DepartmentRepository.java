package com.hrm.departmentservice.repository;


import com.hrm.departmentservice.entities.Department;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author LIULE9
 * @create 2019-03-11 2:10 PM
 */
@Repository
public interface DepartmentRepository
    extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {

    List<Department> findByIdIn(List<Long> ids);
}
