package springboot.APIFramework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import springboot.APIFramework.entities.Employee;
import springboot.APIFramework.repository.EmployeeRepository;
import springboot.APIFramework.service.EmployeeService;
import springboot.APIFramework.serviceimpl.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    EmployeeService employeeService;

    @BeforeEach
    void initUseCase() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void employee_exists_in_db_success() {
        Employee employee = new Employee();
        employee.setName("Naveen");
        employee.setSalary("19999");
        employee.setDepartment("ece");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        // providing knowledge
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> fetchedemployees = employeeService. getAllEmployees();
        assertThat(fetchedemployees.size()).isGreaterThan(0);
    }
}

