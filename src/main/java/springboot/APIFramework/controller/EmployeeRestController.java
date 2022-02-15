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


    @PostMapping
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

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        return new ResponseEntity<String>( "Employee deleted successfully!",HttpStatus.OK);
    }
}
