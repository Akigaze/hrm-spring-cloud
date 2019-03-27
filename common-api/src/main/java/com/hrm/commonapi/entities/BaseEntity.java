package com.hrm.commonapi.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author LIULE9
 * @create 11/03/2019
 */
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = -4174182924953793963L;

  @CreatedDate
  @Column(name = "CREATE_TIME", updatable = false)
  private ZonedDateTime createTime = ZonedDateTime.now();

  @CreatedBy
  @Column(name = "CREATE_BY", updatable = false)
  private String createBy;

  @LastModifiedDate
  @Column(name = "UPDATE_TIME")
  private ZonedDateTime updateTime;

  @LastModifiedBy
  @Column(name = "UPDATE_BY")
  private String updateBy;

  @Version
  @Column(name = "VERSION")
  private Long version;
}
