package springboot.APIFramework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.APIFramework.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}