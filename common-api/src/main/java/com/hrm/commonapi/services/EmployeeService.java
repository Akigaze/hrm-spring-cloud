package com.hrm.commonapi.services;

import com.hrm.commonapi.dto.EmployeeDTO;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 * @author LIULE9
 * @create 11/03/2019
 */
public interface EmployeeService {

    EmployeeDTO getOne(Long employeeId);

    List<EmployeeDTO> findAll();

    void save(EmployeeDTO employeeDTO);

    void deleteById(Long id);

    Page<EmployeeDTO> findByCriteria(EmployeeDTO employeeDTO, Integer curPage, Integer pageSize);

    void update(EmployeeDTO employeeDTO);
}