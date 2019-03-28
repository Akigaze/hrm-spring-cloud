package com.hrm.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
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
public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = -7229588852420224446L;
    private Long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String mobilePhone;

    private String idCard;

    private String englishName;

    private String nationality;

    private String birthplace;

    private String monthlySalary;

    private String gender;

    private DepartmentDTO department;

//    @JsonIgnore
//    public Long getDepartmentId(){
//        return department.getId();
//    }
//
//    @JsonIgnore
//    public String getDepartmentName(){
//        return department.getName();
//    }
}