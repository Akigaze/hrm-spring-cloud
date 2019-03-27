package com.hrm.employeeservice.controller;

import com.hrm.commonapi.dto.EmployeeDTO;
import com.hrm.commonapi.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LIULE9
 * @create 11/03/2019
 */
@RestController
@CrossOrigin
public class EmployeeController {
  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/{id}")
  public EmployeeDTO getOne(@PathVariable("id") Long employeeId) {
    return employeeService.getOne(employeeId);
  }

  @PostMapping("list")
  public Page<EmployeeDTO> findAll(
      @RequestBody EmployeeDTO employeeDTO,
      @RequestParam(defaultValue = "1", required = false) Integer curPage,
      @RequestParam(defaultValue = "20", required = false) Integer pageSize) {
    return employeeService.findByCriteria(employeeDTO, curPage - 1, pageSize);
  }

  @GetMapping
  public List<EmployeeDTO> findAll() {
    return employeeService.findAll();
  }

  @PostMapping
  public ResponseEntity save(@RequestBody EmployeeDTO employeeDTO) {
    employeeService.save(employeeDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping
  public ResponseEntity update(@RequestBody EmployeeDTO employeeDTO) {
    employeeService.update(employeeDTO);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") Long id) {
    employeeService.deleteById(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
