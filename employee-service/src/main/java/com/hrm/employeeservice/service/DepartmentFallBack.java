package com.hrm.employeeservice.service;

import com.hrm.common.dto.DepartmentDTO;
import feign.hystrix.FallbackFactory;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DepartmentFallBack implements FallbackFactory<DepartmentService> {

  @Override
  public DepartmentService create(Throwable throwable) {
    return new DepartmentService() {

      @Override
      public DepartmentDTO getOne(Long departmentId) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("wrong department");
        return dto;
      }

      @Override
      public List<DepartmentDTO> getByIds(List<Long> ids) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("wrong ids");
        return Arrays.asList(dto);
      }

      @Override
      public List<DepartmentDTO> getAll() {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("found nothing");
        return Arrays.asList(dto);
      }

      @Override
      public DepartmentDTO findById(Long id) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("wrong id");
        return dto;
      }
    };
  }
}
