package com.excellence.spring_boot_testing.repository;

import com.excellence.spring_boot_testing.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = Employee.builder()
                .firstName("Vijay")
                .lastName("Singh")
                .email("vijay@gmail.com")
                .build();
    }

    // Junit test operation for save employee operation
    // @DisplayName("Junit test operation for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Vijay")
//                .lastName("Singh")
//                .email("vijay@gmail.com")
//                .build();

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    // Junit tests for get all employees operation
    @DisplayName("Junit tests for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Vijay")
//                .lastName("Singh")
//                .email("vijay@gmail.com")
//                .build();

        Employee employee1 = Employee.builder()
                .firstName("Aashu")
                .lastName("Singh")
                .email("aashu@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();


        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    // Junit tests for get employee by id operation
    @DisplayName("Junit tests for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Vijay")
//                .lastName("Singh")
//                .email("vijay@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // Junit tests for get employee by email operation
    @DisplayName("Junit tests for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Vijay")
//                .lastName("Singh")
//                .email("vijay@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // Junit tests for update employee operation
    @DisplayName("Junit tests for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdatedEmployee_thenReturnUpdatedEmployee() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Vijay")
//                .lastName("Singh")
//                .email("vijay@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByEmail(employee.getEmail()).get();
        savedEmployee.setEmail("aashu@gmail.com");
        savedEmployee.setFirstName("Aashu");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("aashu@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Aashu");
    }

    // Junit tests for delete employee operation
    @DisplayName("Junit tests for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Vijay")
//                .lastName("Singh")
//                .email("vijay@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(optionalEmployee).isEmpty();
    }

    // Junit tests for custom query using JPQL with index
    @DisplayName("Junit tests for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Vijay")
//                .lastName("Singh")
//                .email("vijay@gmail.com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "Vijay";
        String lastName = "Singh";

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJpql(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    // Junit tests for custom query using JPQL with Named params
    @DisplayName("Junit tests for custom query using JPQL with Named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Vijay")
//                .lastName("Singh")
//                .email("vijay@gmail.com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "Vijay";
        String lastName = "Singh";

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    // Junit tests for custom query using native SQL with index
    @DisplayName("Junit tests for custom query using native SQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSql_thenReturnEmployeeObject() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Vijay")
//                .lastName("Singh")
//                .email("vijay@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    // Junit tests for custom query using native SQL with named params
    @DisplayName("Junit tests for custom query using native SQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSqlNamedParams_thenReturnEmployeeObject() {

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Vijay")
//                .lastName("Singh")
//                .email("vijay@gmail.com")
//                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

}
