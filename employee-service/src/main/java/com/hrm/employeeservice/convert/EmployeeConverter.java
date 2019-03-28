package com.hrm.employeeservice.convert;

import static java.util.stream.Collectors.toList;

import com.hrm.commonapi.dto.DepartmentDTO;
import com.hrm.commonapi.dto.EmployeeDTO;
import com.hrm.employeeservice.entities.Employee;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    DepartmentDTO departmentDTO= new DepartmentDTO();
    departmentDTO.setId(employee.getDepartmentId());
    BeanUtils.copyProperties(employee, employeeDTO);
    employeeDTO.setDepartment(departmentDTO);
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

  public List<EmployeeDTO> convert2DTOS(List<Employee> employees, List<DepartmentDTO> departments) {
    List<EmployeeDTO> employeeDTOS = this.convert2DTOS(employees);
    employeeDTOS.forEach(employee -> {
      departments.stream()
          .filter(department -> department.getId().equals(employee.getDepartmentId())).findFirst()
          .ifPresent(employee::setDepartment);
    });
    return employeeDTOS;
  }
}
