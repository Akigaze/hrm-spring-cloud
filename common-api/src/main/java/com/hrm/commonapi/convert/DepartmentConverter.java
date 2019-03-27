package com.hrm.commonapi.convert;

import static java.util.stream.Collectors.toList;

import com.hrm.commonapi.dto.DepartmentDTO;
import com.hrm.commonapi.entities.Department;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author LIULE9
 * @create 11/03/2019
 */
@Slf4j
@Component
public class DepartmentConverter {

  public DepartmentDTO convert2DTO(Department department) {
    DepartmentDTO departmentDTO = new DepartmentDTO();
    BeanUtils.copyProperties(department, departmentDTO);
    return departmentDTO;
  }

  public List<DepartmentDTO> convert2DTOS(List<Department> departments) {
    return departments.stream().map(this::convert2DTO).collect(toList());
  }

  public Department convertEntity(DepartmentDTO departmentDTO) {
    Department department = new Department();
    BeanUtils.copyProperties(departmentDTO, department);
    return department;
  }
}
