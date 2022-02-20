package springboot.APIFramework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.APIFramework.entities.Employee;
import springboot.APIFramework.service.EmployeeService;

import java.util.List;
@RestController
public class EmployeeRestController {
    private  EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        super();
        this.employeeService=employeeService;
    }


    @PostMapping("/create")
    public ResponseEntity<Employee>saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
    }
    @GetMapping
    public List<Employee> getallEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable ("id") long employeeId) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>( "Employee deleted successfully!",HttpStatus.OK);
    }
    @GetMapping(value = "/salary")
    public List<Employee> getEmployeeBySalary() {
        return employeeService.findAllOrderBySalaryAsc();
    }

    @GetMapping(value = "/salary/{id}")
    public String getEmployeeSalary(@PathVariable long id) {
        return employeeService.getEmployeeSalary(id);
    }
    @GetMapping("/viewDept/{department}")
    public List<Employee> findByDepartment(@PathVariable String department) {
        return employeeService.findByDepartment(department);
    }

}

