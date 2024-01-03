package com.excellence.spring_boot_testing.service;

import com.excellence.spring_boot_testing.exception.ResourceNotFoundException;
import com.excellence.spring_boot_testing.model.Employee;
import com.excellence.spring_boot_testing.repository.EmployeeRepository;
import com.excellence.spring_boot_testing.service.impl.EmployeeServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private Employee employee;

    @BeforeEach
    public void setup() {
        //        employeeRepository = Mockito.mock(EmployeeRepository.class);
        //        employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1L)
                .firstName("Vijay")
                .lastName("Singh")
                .email("vijay@gmail.com")
                .build();
    }

    // Junit test for saveEmployee method
    @DisplayName("Junit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        // given - precondition or setup
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        // when - action or the behaviour that we are going test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.saveEmployee(employee);
        });

        // then - verify the output
        verify(employeeRepository, never()).save(any(Employee.class));

    }

    // Junit tests for getAllEmployees method
    @DisplayName("Junit tests for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() {

        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Aashu")
                .lastName("Singh")
                .email("aashu@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

        // when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeService.getAllEmployees();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    // Junit tests for getAllEmployees method
    @DisplayName("Junit tests for getAllEmployees method (negative scenario)")
    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList() {

        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Aashu")
                .lastName("Singh")
                .email("aashu@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeService.getAllEmployees();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(0);

    }

    // Junit tests for getEmployeeById method
    @DisplayName("Junit tests for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() {

        // given - precondition or setup
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeService.getEmployeeById(employee.getId()).get();

        // then
        assertThat(savedEmployee).isNotNull();

    }

    // Junit test for updateEmployee Method
    @Test
    @DisplayName("Junit test for updateEmployee Method")
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

        // given - precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmail("victory@gmail.com");
        employee.setFirstName("Victory");

        // when - action or the behaviour that we are going test
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("victory@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Victory");
    }

    // Junit test for deleteEmployee method
    @DisplayName("Junit test for deleteEmployee method")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNothing() {
        // given - precondition or setup
        long employeeId = 1L;

        willDoNothing().given(employeeRepository).deleteById(employeeId);

        // when - action or the behaviour that we are going test
        employeeService.deleteEmployee(employeeId);

        // then - verify the output
        verify(employeeRepository, times(1)).deleteById(employeeId);

    }


}
