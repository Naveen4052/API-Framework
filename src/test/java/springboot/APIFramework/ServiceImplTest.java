package springboot.APIFramework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import springboot.APIFramework.entities.Employee;
import springboot.APIFramework.repository.EmployeeRepository;
import springboot.APIFramework.service.EmployeeService;
import springboot.APIFramework.serviceimpl.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    Employee e1 = new Employee(1, "naveen", "100000", "software");
    Employee e2 = new Employee(2, "amit", "10000", "finance");
    Employee e3 = new Employee(3, "ram", "20000", "marketing");


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

        List<Employee> fetchedEmployees = employeeService.getAllEmployees();
        assertThat(fetchedEmployees.size()).isGreaterThan(0);
    }

    @Test
    public void employee_existsById_in_db_success() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Naveen");
        employee.setSalary("19999");
        employee.setDepartment("ece");

        // providing knowledge
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        assertThat(employeeService.getEmployeeById(1).equals(employee));
    }

    @Test
    public void employee_save_in_db_success() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Naveen");
        employee.setSalary("19999");
        employee.setDepartment("ece");
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        assertThat(employeeService.saveEmployee(employee)).isEqualTo(employee);
    }

    @Test
    public void employee_findByDepartment() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Naveen");
        employee.setSalary("19999");
        employee.setDepartment("ece");

        Employee employee1 = new Employee();
        employee1.setId(2);
        employee1.setName("ram");
        employee1.setSalary("19999");
        employee1.setDepartment("ece");
        List<Employee> e1 = new ArrayList<>();
        e1.add(employee);
        e1.add(employee1);

        when(employeeRepository.findByDepartment(employee.getDepartment())).thenReturn(e1);
        assertThat(employeeService.findByDepartment("ece").equals(e1));
    }

    @Test
    public void employee_deleteEmployee_in_db_success() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Naveen");
        employee.setSalary("19999");
        employee.setDepartment("ece");

//        Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepository.existsById(employee.getId())).thenReturn(false);
        assertFalse(employeeRepository.existsById(employee.getId()));
    }

    @Test
    public void employee_updateEmployee_in_db_success() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Naveen");
        employee.setSalary("19999");
        employee.setDepartment("ece");

        Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        employee.setName("ram");
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        assertThat(employeeService.updateEmployee(employee, 1)).isEqualTo(employee);

    }
}