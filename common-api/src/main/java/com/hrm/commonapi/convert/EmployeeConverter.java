package com.hrm.commonapi.convert;

import static java.util.stream.Collectors.toList;

import com.hrm.commonapi.dto.DepartmentDTO;
import com.hrm.commonapi.dto.EmployeeDTO;
import com.hrm.commonapi.entities.Department;
import com.hrm.commonapi.entities.Employee;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author LIULE9
 * @create 11/03/2019
 */
@Slf4j
@Component
public class EmployeeConverter {

  public EmployeeDTO convert2DTO(Employee employee) {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    BeanUtils.copyProperties(employee, employeeDTO);
    Department department = employee.getDepartment();
    if (Objects.nonNull(department)) {
      employeeDTO.setDepartment(new DepartmentDTO(department.getId(), department.getName()));
    }
    return employeeDTO;
  }

  public List<EmployeeDTO> convert2DTOS(List<Employee> employees) {
    return employees.stream().map(this::convert2DTO).collect(toList());
  }

  public Employee convert2Entity(EmployeeDTO employeeDTO) {
    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeDTO, employee);
    return employee;
  }
}
