package com.hrm.commonapi.services;

import com.hrm.commonapi.dto.DepartmentDTO;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author LIULE9
 * @create 2019-03-11 7:28 PM
 */
public interface DepartmentService {
    DepartmentDTO getOne(Long departmentId);

    List<DepartmentDTO> findAll();

    void save(DepartmentDTO departmentDTO);

    void deleteById(Long id);

    Page<DepartmentDTO> findByCriteria(DepartmentDTO departmentDTO, Integer curPage, Integer pageSize);

    void update(DepartmentDTO departmentDTO);

    List<DepartmentDTO> findByIds(List<Long> ids);
}