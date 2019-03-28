package com.hrm.departmentservice.repository;

import com.hrm.departmentservice.entities.Department;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
public class DepartmentRepositoryTest {
  private Department cargoSmart = new Department(1L, "CargoSmart", new ArrayList<>());
  private Department iris = new Department(2L, "Iris", new ArrayList<>());

  @Autowired
  private DepartmentRepository repository;

  @Autowired
  private TestEntityManager entityManager;

  @After
  public void tearDown() throws Exception {
    entityManager.clear();
  }

  @Test
  public void should_get_all_department() {
    entityManager.persist(cargoSmart);
    entityManager.persist(iris);

    List<Department> departments = repository.findAll();

    assertThat(departments.size(), is(2));
    assertThat(departments.get(0).getName(), is("CargoSmart"));
    assertThat(departments.get(1).getName(), is("Iris"));
  }

  @Test
  public void should_get_the_specific_department_and_its_employees_by_id() {
    entityManager.persist(cargoSmart);
    Long id = entityManager.persistAndGetId(iris, Long.class);

    Department found = repository.findById(id).get();

    assertThat(found.getName(), is("Iris"));
  }

  @Test
  public void should_save_the_given_department_to_db() {
    entityManager.persist(cargoSmart);

    Department saved = repository.save(iris);
    Department second = entityManager.find(Department.class, saved.getId());

    assertThat(saved.getName(), is("Iris"));
    assertThat(second.getName(), is("Iris"));
  }

  @Test
  public void should_delete_the_given_department_from_db() {
    entityManager.persist(cargoSmart);
    Long id = entityManager.persistAndGetId(iris, Long.class);

    repository.delete(iris);
    Department second = entityManager.find(Department.class, id);

    assertThat(second, equalTo(null));
  }
}