package com.hrm.employeeservice.entities;

import com.hrm.common.entities.BaseEntity;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author Leo Liu
 * @create 11/03/2019
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DynamicUpdate
@DynamicInsert
public class Employee extends BaseEntity {
  private static final long serialVersionUID = -3894079727069874921L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false, updatable = false)
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "BIRTHDAY", nullable = false)
  private LocalDate birthday;

  @Column(name = "MOBILE_PHONE", nullable = false)
  private String mobilePhone;

  @Column(name = "ID_CARD", nullable = false)
  private String idCard;

  @Column(name = "ENGLISH_NAME", nullable = false)
  private String englishName;

  @Column(name = "NATIONALITY", nullable = false)
  private String nationality;

  @Column(name = "BIRTHPLACE", nullable = false)
  private String birthplace;

  @Column(name = "MONTHLY_SALARY", nullable = false)
  private String monthlySalary;

  @Column(name = "GENDER", nullable = false)
  private String gender;

  @Column(name = "DEPARTMENT_ID")
  private Long departmentId;
}
