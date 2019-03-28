package com.hrm.departmentservice.service;

import com.google.common.collect.Lists;
import com.hrm.common.dto.DepartmentDTO;
import com.hrm.departmentservice.convert.DepartmentConverter;
import com.hrm.departmentservice.entities.Department;
import com.hrm.departmentservice.repository.DepartmentRepository;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DepartmentServiceImplTest {

    private DepartmentRepository departmentRepository;

    @Test
    public void should_save_the_given_department() {
        departmentRepository = mock(DepartmentRepository.class);
        DepartmentConverter converter = mock(DepartmentConverter.class);
        DepartmentServiceImpl service = new DepartmentServiceImpl(departmentRepository, new DepartmentConverter());
        Department department = new Department();
        department.setId(1L);
        department.setName("IRIS");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "IRIS");

        when(converter.convertEntity(departmentDTO)).thenReturn(department);
        when(departmentRepository.save(department)).thenReturn(department);

        service.save(departmentDTO);

        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    public void should_get_info_of_specific_department_when_give_the_id() {
        departmentRepository = mock(DepartmentRepository.class);
        DepartmentServiceImpl service = new DepartmentServiceImpl(departmentRepository, new DepartmentConverter());
        Department department = new Department();
        department.setName("IRIS");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        DepartmentDTO one = service.getOne(1L);

        assertThat(department.getName(), is(one.getName()));
    }

    @Test
    public void should_get_info_of_all_departments() {
        departmentRepository = mock(DepartmentRepository.class);
        DepartmentServiceImpl service = new DepartmentServiceImpl(departmentRepository, new DepartmentConverter());
        Department department1 = new Department();
        department1.setName("IRIS");
        Department department2 = new Department();
        department1.setName("CargoSmart");
        List<Department> departments = Lists.newArrayList(department1, department2);

        when(departmentRepository.findAll()).thenReturn(departments);
        List<DepartmentDTO> list = service.findAll();

        assertThat(departments.size(), is(list.size()));
    }

    @Test
    public void should_delete_specific_department_when_give_the_id() {
        departmentRepository = mock(DepartmentRepository.class);
        DepartmentServiceImpl service = new DepartmentServiceImpl(departmentRepository, new DepartmentConverter());
        Department department1 = new Department();

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department1));
        service.deleteById(1L);

        verify(departmentRepository, times(1)).delete(department1);
    }

    @Test
    public void should_update_specific_department_when_give_the_id() {
        departmentRepository = mock(DepartmentRepository.class);
        DepartmentServiceImpl service = new DepartmentServiceImpl(departmentRepository, new DepartmentConverter());
        Department department1 = new Department();
        department1.setName("IRIS");
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("CargoSmart");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department1));
        service.update(departmentDTO);

        assertThat("CargoSmart", is(department1.getName()));
    }

    @Test
    public void should_get_info_of_specific_department_when_give_the_ids() {
        departmentRepository = mock(DepartmentRepository.class);
        DepartmentServiceImpl service = new DepartmentServiceImpl(departmentRepository, new DepartmentConverter());
        Department department = new Department();
        department.setName("IRIS");
        List<Long> ids = Lists.newArrayList();
        ids.add(1L);
        List<Department> departments = Lists.newArrayList();
        departments.add(department);

        when(departmentRepository.findByIdIn(ids)).thenReturn(departments);
        List<DepartmentDTO> departmentDTOS = service.findByIds(ids);

        assertThat(departmentDTOS.size(), is(departments.size()));
    }
}
