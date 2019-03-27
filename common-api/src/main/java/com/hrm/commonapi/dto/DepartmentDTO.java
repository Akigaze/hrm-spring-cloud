package com.hrm.commonapi.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LIULE9
 * @create 11/03/2019
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO implements Serializable {

  private static final long serialVersionUID = 532746296155084560L;

  private Long id;
  private String name;
}
