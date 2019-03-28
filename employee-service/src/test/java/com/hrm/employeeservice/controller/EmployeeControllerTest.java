package com.hrm.employeeservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrm.common.dto.DepartmentDTO;
import com.hrm.common.dto.EmployeeDTO;
import com.hrm.departmentservice.entities.Department;
import com.hrm.departmentservice.repository.DepartmentRepository;
import com.hrm.employeeservice.entities.Employee;
import com.hrm.employeeservice.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.hrm.admin"})
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class EmployeeControllerTest {
  private Department cargoSmart = new Department(1L, "CargoSmart", new ArrayList<>());
  private Employee quinn = new Employee(1L, "Quinn", LocalDate.now(), "134322561", "4421334199511112356", "Quinn Huang", "汉", "广州","男", "2125121",
      cargoSmart.getId());
  private Employee jeffery = new Employee(2L, "Jeffery", LocalDate.now(), "155120356", "4421334199602124545", "Jeffery Lu", "汉", "深圳","男", "2125121",
      cargoSmart.getId());
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper mapper;

  @MockBean
  private DepartmentRepository departmentRepository;
  @MockBean
  private EmployeeRepository employeeRepository;

  @Test
  public void should_get_all_employees() throws Exception {
    List<Employee> employees = Arrays.asList(quinn, jeffery);
    given(employeeRepository.findAll()).willReturn(employees);

    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].name", is("Quinn")))
        .andExpect(jsonPath("$[1].name", is("Jeffery")));
  }

  @Test
  public void should_get_info_of_specific_employee_when_give_the_id() throws Exception {

    given(employeeRepository.findById(1L)).willReturn(Optional.of(quinn));

    mockMvc.perform(get("/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Quinn")))
        .andExpect(jsonPath("$.id", is(1)));
  }

  @Test
  public void should_save_the_given_employee() throws Exception {
    Employee leo = new Employee();
    leo.setName("Leo");
    Employee saved = new Employee(1L, "Leo", LocalDate.now(), "137159852", "4421334199403235687", "Leo Liu", "汉", "珠海","120", "男",
        cargoSmart.getId());
    EmployeeDTO dto = new EmployeeDTO(1L, "Leo", LocalDate.now(), "137159852", "4421334199403235687", "Leo Liu", "汉", "珠海","120", "男", new DepartmentDTO(1L, "CargoSmart"));

    given(departmentRepository.findById(1L)).willReturn(Optional.of(cargoSmart));
    given(employeeRepository.save(leo)).willReturn(saved);

    ResultActions result = mockMvc.perform(
        post("/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(dto))
    );

    result.andExpect(status().isCreated()).andDo(print());
    verify(employeeRepository, times(1)).save(saved);
  }

  @Test
  public void should_delete_the_specific_employee_when_give_id() throws Exception {
    given(employeeRepository.findById(1L)).willReturn(Optional.of(quinn));
    doNothing().when(employeeRepository).delete(quinn);

    ResultActions result = mockMvc.perform(delete("/1"));

    result.andExpect(status().isNoContent()).andDo(print());
    verify(employeeRepository, times(1)).findById(1L);
    verify(employeeRepository, times(1)).delete(quinn);
  }
  @Test
  public void should_update_the_specific_employee_when_give_id() throws Exception {
    Employee originEmployee = new Employee(1L, "SomeBody", LocalDate.now(), "137159852", "4421334199403235687", "Leo Liu", "汉", "珠海","120", "男",
        cargoSmart.getId());
    EmployeeDTO dto = new EmployeeDTO(1L, "Quinn", LocalDate.now(), "137159852", "4421334199403235687", "Leo Liu", "汉", "珠海","120", "男", new DepartmentDTO(1L, "CargoSmart"));

    given(employeeRepository.findById(1L)).willReturn(Optional.of(originEmployee));
    given(departmentRepository.findById(1L)).willReturn(Optional.of(cargoSmart));

    ResultActions result = mockMvc.perform(
        put("/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(dto))
    );

    result.andExpect(status().isNoContent()).andDo(print());
    verify(employeeRepository, times(1)).findById(1L);
    assertThat(originEmployee.getName(), is("Quinn"));
  }
}
