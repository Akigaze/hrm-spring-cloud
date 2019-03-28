package com.hrm.employeeservice.service;

import com.hrm.commonapi.dto.DepartmentDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "department-service")
public interface DepartmentFeign {

  @GetMapping("/{id}")
  DepartmentDTO getOne(@PathVariable("id") Long departmentId);

  @PostMapping("/ids")
  List<DepartmentDTO> getByIds(@RequestBody List<Long> ids);

  @GetMapping
  List<DepartmentDTO> getAll();
}
