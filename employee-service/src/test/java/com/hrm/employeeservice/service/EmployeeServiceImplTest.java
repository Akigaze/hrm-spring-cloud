package com.hrm.employeeservice.service;

import com.google.common.collect.Lists;
import com.hrm.common.dto.DepartmentDTO;
import com.hrm.common.dto.EmployeeDTO;
import com.hrm.employeeservice.convert.EmployeeConverter;
import com.hrm.employeeservice.entities.Employee;
import com.hrm.employeeservice.repository.EmployeeRepository;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

  private EmployeeRepository employeeRepository;
  private DepartmentService departmentService;

  @Test
  public void should_get_one_employee_by_id(){
    employeeRepository = mock(EmployeeRepository.class);
    departmentService = mock(DepartmentService.class);
    EmployeeConverter converter = mock(EmployeeConverter.class);
    EmployeeServiceImpl service = new EmployeeServiceImpl(employeeRepository, new EmployeeConverter(),
        departmentService);

    Employee employee = new Employee();
    employee.setName("Quinn");
    when(converter.convert2DTO(employee)).thenReturn(new EmployeeDTO());
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    EmployeeDTO one = service.getOne(1L);
    assertThat("Quinn",is(one.getName()));
  }

  @Test
  public void should_get_info_of_all_employees() {
    employeeRepository = mock(EmployeeRepository.class);
    departmentService = mock(DepartmentService.class);
    EmployeeServiceImpl service = new EmployeeServiceImpl(employeeRepository, new EmployeeConverter(),
        departmentService);
    Employee employee1 = new Employee();
    employee1.setName("Tracy");
    Employee employee2 = new Employee();
    employee2.setName("Quinn");
    List<Employee> employees = Lists.newArrayList(employee1, employee2);
    List<DepartmentDTO> departments = Lists.newArrayList();

    when(employeeRepository.findAll()).thenReturn(employees);
    when(departmentService.getAll()).thenReturn(departments);
    List<EmployeeDTO> list = service.findAll();

    assertThat(employees.size(), is(list.size()));
  }

  @Test
  public void should_delete_specific_employee_when_give_the_id() {
    employeeRepository = mock(EmployeeRepository.class);
    EmployeeServiceImpl service = new EmployeeServiceImpl(employeeRepository, new EmployeeConverter(),
        departmentService);
    Employee employee = new Employee();

    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    service.deleteById(1L);

    verify(employeeRepository, times(1)).delete(employee);
  }
}