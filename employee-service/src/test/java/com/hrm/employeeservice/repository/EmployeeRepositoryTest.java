package com.hrm.employeeservice.repository;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import com.hrm.departmentservice.entities.Department;
import com.hrm.employeeservice.entities.Employee;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
public class EmployeeRepositoryTest {
  private Department cargoSmart = new Department(1L, "CargoSmart", new ArrayList<>());
  private Employee quinn = new Employee(1L, "Quinn", LocalDate.now(), "134322561", "4421334199511112356", "Quinn Huang", "汉", "广州","男", "2125121",
      cargoSmart.getId());
  private Employee jeffery = new Employee(2L, "Jeffery", LocalDate.now(), "155120356", "4421334199602124545", "Jeffery Lu", "汉", "深圳","男", "2125121",
      cargoSmart.getId());
  private Employee leo = new Employee(3L, "Leo", LocalDate.now(), "137159852", "4421334199403235687", "Leo Liu", "汉", "珠海","120", "男",
      cargoSmart.getId());

  @Autowired
  private EmployeeRepository repository;

  @Autowired
  private TestEntityManager entityManager;

  @After
  public void tearDown() throws Exception {
    entityManager.clear();
  }

  @Test
  public void should_get_all_employees() {
    entityManager.persist(quinn);
    entityManager.persist(jeffery);
    entityManager.persist(leo);

    List<Employee> employees = repository.findAll();

    assertThat(employees.size(), is(3));
    assertThat(employees.get(0), equalTo(quinn));
    assertThat(employees.get(1), equalTo(jeffery));
    assertThat(employees.get(2), equalTo(leo));
  }

  @Test
  public void should_get_the_specific_employee_by_id() {
    entityManager.persist(quinn);
    Long id = entityManager.persistAndGetId(jeffery, Long.class);
    entityManager.persist(leo);

    Employee employee = repository.findById(id).get();

    assertThat(employee, equalTo(jeffery));
  }

  @Test
  public void should_save_the_specific_employee_to_db() {
    entityManager.persist(quinn);

    Employee saved = repository.save(jeffery);
    Employee second = entityManager.find(Employee.class, jeffery.getId());

    assertThat(saved, equalTo(jeffery));
    assertThat(second, equalTo(jeffery));
  }

  @Test
  public void should_delete_specific_employee_from_db() {
    entityManager.persist(quinn);
    entityManager.persist(jeffery);

    repository.delete(jeffery);
    Employee found = entityManager.find(Employee.class, jeffery.getId());

    assertThat(found, equalTo(null));
  }
}