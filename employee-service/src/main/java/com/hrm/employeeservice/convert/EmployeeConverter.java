package com.hrm.employeeservice.convert;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

import com.hrm.common.dto.DepartmentDTO;
import com.hrm.common.dto.EmployeeDTO;
import com.hrm.employeeservice.entities.Employee;
import java.util.List;

import com.hrm.employeeservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LIULE9
 * @create 11/03/2019
 */
@Slf4j
@Component
public class EmployeeConverter {

  @Autowired private DepartmentService departmentService;

  public EmployeeDTO convert2DTO(Employee employee) {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    DepartmentDTO departmentDTO= new DepartmentDTO();
    departmentDTO.setId(employee.getDepartmentId());
//    DepartmentDTO one = departmentFeign.getOne(employee.getDepartmentId());
//    departmentDTO.setName(Objects.nonNull(one)?one.getName():null);
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
    DepartmentDTO department = employeeDTO.getDepartment();
    if (department != null){
      employee.setDepartmentId(department.getId());
    }
    return employee;
  }

  public List<EmployeeDTO> convert2DTOS(List<Employee> employees, List<DepartmentDTO> departments) {
    List<EmployeeDTO> employeeDTOS = this.convert2DTOS(employees);
    employeeDTOS.forEach(employee -> {
      departments.stream()
          .filter(department -> department.getId().equals(employee.getDepartment().getId())).findFirst()
          .ifPresent(employee::setDepartment);
    });
    return employeeDTOS;
  }

  public EmployeeDTO convert2DTO(Employee employee, DepartmentDTO departmentDTO) {
    EmployeeDTO employeeDTO = this.convert2DTO(employee);
    employeeDTO.setDepartment(departmentDTO);
    return employeeDTO;
  }
}
