package springboot.APIFramework.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.APIFramework.entities.Employee;
import springboot.APIFramework.exception.ResourceNotFoundException;
import springboot.APIFramework.repository.EmployeeRepository;
import springboot.APIFramework.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
   private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee=employeeRepository.findById(id);
        return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setDepartment(employee.getDepartment());
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",id));
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllOrderBySalaryAsc() {
        return employeeRepository.findAllOrderBySalaryAsc();
    }
    @Override
    public String getEmployeeSalary(long id)
    {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee","ID",id);
        } else {
            Employee employee=employeeRepository.getById(id);
            return "Salary of the Employee is: " + employee.getSalary();
        }
    }

}
