package springboot.APIFramework.service;

import springboot.APIFramework.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id);
    Employee updateEmployee(Employee employee,long id);
    void deleteEmployee(long id);
    List<Employee> findAllOrderBySalaryAsc();
    public String getEmployeeSalary(long id);

}
