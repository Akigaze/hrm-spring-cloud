package com.hrm.departmentservice.service;


import com.google.common.collect.Lists;
import com.hrm.commonapi.dto.DepartmentDTO;
import com.hrm.commonapi.services.DepartmentService;
import com.hrm.departmentservice.convert.DepartmentConverter;
import com.hrm.departmentservice.entities.Department;
import com.hrm.departmentservice.repository.DepartmentRepository;
import java.util.List;
import java.util.function.Predicate;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author LIULE9
 * @create 11/03/2019
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
  @Autowired
  private DepartmentRepository departmentRepository;
  @Autowired
  private DepartmentConverter departmentConverter;

  @Override
  public DepartmentDTO getOne(Long departmentId) {
    return departmentRepository
        .findById(departmentId)
        .map(department -> departmentConverter.convert2DTO(department))
        .orElse(null);
  }

  @Override
  public List<DepartmentDTO> findAll() {
    List<Department> departments = departmentRepository.findAll();
    return departmentConverter.convert2DTOS(departments);
  }

  public void save(DepartmentDTO departmentDTO) {
    Department department = departmentConverter.convertEntity(departmentDTO);
    departmentRepository.save(department);
  }

  public void deleteById(Long id) {
    departmentRepository
        .findById(id)
        .ifPresent(department -> departmentRepository.delete(department));
  }

  public void update(DepartmentDTO departmentDTO) {
    departmentRepository
        .findById(departmentDTO.getId())
        .ifPresent(department -> department.setName(departmentDTO.getName()));
  }

  @Override
  public List<DepartmentDTO> findByIds(List<Long> ids) {
    List<Department> departments = departmentRepository.findByIdIn(ids);
    return departmentConverter.convert2DTOS(departments);
  }

  public Page<DepartmentDTO> findByCriteria(
      DepartmentDTO departmentDTO, Integer curPage, Integer pageSize) {
//    PageRequest pageRequest = PageRequest.of(curPage, pageSize);
//    Specification<Department> specification = buildCriteria(departmentDTO);
//    Page<Department> page = departmentRepository.findAll();
    return null;
  }

  private Specification<Department> buildCriteria(DepartmentDTO departmentDTO) {
//    return (root, criteriaQuery, criteriaBuilder) -> {
//      List<Predicate> list = Lists.newArrayList();
//      if (StringUtils.isNotBlank(departmentDTO.getName())) {
//        list.add(criteriaBuilder.like(root.get("name"), "%" + departmentDTO.getName() + "%"));
//      }
//      Predicate[] predicates = new Predicate[list.size()];
//      return criteriaQuery.where(predicates).getRestriction();
//    };
    return null;
  }

}
