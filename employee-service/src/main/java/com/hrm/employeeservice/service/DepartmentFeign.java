package com.hrm.employeeservice.service;

import com.hrm.commonapi.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "department-service")
public interface DepartmentFeign {

  @GetMapping("/{id}")
  DepartmentDTO getOne(@PathVariable("id") Long departmentId);
}
