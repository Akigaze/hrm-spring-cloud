package com.hrm.departmentservice.controller;
import com.hrm.common.dto.DepartmentDTO;
import com.hrm.common.services.DepartmentService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LIULE9
 * @create 11/03/2019
 */
@RestController
@CrossOrigin
public class DepartmentController {
  @Autowired
  private DepartmentService departmentService;

  @GetMapping("/{id}")
  public DepartmentDTO getOne(@PathVariable("id") Long departmentId) {
    return departmentService.getOne(departmentId);
  }

  @GetMapping
  public List<DepartmentDTO> findAll() {
    return departmentService.findAll();
  }

  @PostMapping("/ids")
  public List<DepartmentDTO> getByIds(@RequestBody List<Long> ids){
    return departmentService.findByIds(ids);
  }

  @PostMapping
  public ResponseEntity save(@RequestBody DepartmentDTO departmentDTO) {
    departmentService.save(departmentDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping
  public ResponseEntity update(@RequestBody DepartmentDTO departmentDTO) {
    departmentService.update(departmentDTO);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") Long id) {
    departmentService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("list")
  public Page<DepartmentDTO> findAll(
      @RequestBody DepartmentDTO departmentDTO,
      @RequestParam(defaultValue = "1", required = false) Integer curPage,
      @RequestParam(defaultValue = "20", required = false) Integer pageSize) {
    return departmentService.findByCriteria(departmentDTO, curPage - 1, pageSize);
  }
}
