package com.hrm.departmentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrm.common.dto.DepartmentDTO;
import com.hrm.departmentservice.entities.Department;
import com.hrm.departmentservice.repository.DepartmentRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.hrm.admin"})
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
public class DepartmentControllerTest {
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper mapper;

  @MockBean private DepartmentRepository repository;

  @Test
  public void should_get_info_of_specific_department_when_give_the_id() throws Exception {
    Department iris = new Department(1L, "Iris", new ArrayList<>());

    given(repository.findById(1L)).willReturn(Optional.of(iris));

    mockMvc.perform(get("/departments/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Iris")))
        .andExpect(jsonPath("$.id", is(1)));
  }

  @Test
  public void should_get_info_of_all_departments() throws Exception {
    Department iris = new Department(1L, "Iris", new ArrayList<>());
    Department cargoSmart= new Department(2L, "CargoSmart", new ArrayList<>());
    List<Department> departments= Arrays.asList(iris, cargoSmart);

    given(repository.findAll()).willReturn(departments);

    mockMvc.perform(get("/departments")).andExpect(status().isOk())
        .andExpect(jsonPath("$",hasSize(2)))
        .andExpect(jsonPath("$[0].name", is("Iris")))
        .andExpect(jsonPath("$[1].name", is("CargoSmart")))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[1].id", is(2)));
  }

  @Test
  public void should_save_the_given_department() throws Exception {
    Department iris = new Department();
    iris.setName("Iris");
    Department saved = new Department(1L, "Iris", new ArrayList<>());
    given(repository.save(iris)).willReturn(saved);

    ResultActions result = mockMvc.perform(
        post("/departments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(new DepartmentDTO(1L,"Iris")))
    );

    result.andExpect(status().isCreated()).andDo(print());
    verify(repository, times(1)).save(saved);
  }

  @Test
  public void should_delete_the_specific_department_when_give_id() throws Exception {
    Department iris = new Department(1L, "Iris", new ArrayList<>());
    given(repository.findById(1L)).willReturn(Optional.of(iris));
    doNothing().when(repository).delete(iris);

    ResultActions result = mockMvc.perform(delete("/departments/1"));

    result.andExpect(status().isNoContent()).andDo(print());
    verify(repository, times(1)).findById(1L);
    verify(repository, times(1)).delete(iris);
  }

  @Test
  public void should_update_the_specific_department_when_give_id() throws Exception {
    Department originDepartment = new Department(1L, "Iris", new ArrayList<>());
    given(repository.findById(1L)).willReturn(Optional.of(originDepartment));

    ResultActions result = mockMvc.perform(
        put("/departments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(new DepartmentDTO(1L,"CargoSmart")))
    );

    result.andExpect(status().isNoContent()).andDo(print());
    verify(repository, times(1)).findById(1L);
    assertThat(originDepartment.getName(), is("CargoSmart"));
  }

  //TODO not sure how to test
  @Ignore
  public void should_search_departments_by_the_given_criteria() throws Exception {
    DepartmentDTO criteriaDto = new DepartmentDTO();
    criteriaDto.setName("Iris");
    Department iris2 = new Department(1L, "Iris2", new ArrayList<>());
    Department iris4 = new Department(2L, "Iris4", new ArrayList<>());
    List<Department> departments = Arrays.asList(iris2, iris4);
    PageImpl<Department> found = new PageImpl<>(departments, PageRequest.of(1,2), 2);
    given(repository.findAll(any(Specification.class), eq(PageRequest.of(1,2)))).willReturn(found);

    ResultActions result = mockMvc.perform(
        get("/departments/list")
            .param("curPage", "1")
            .param("pageSize", "2")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(criteriaDto))
    );

    result.andExpect(status().isOk());
  }
}