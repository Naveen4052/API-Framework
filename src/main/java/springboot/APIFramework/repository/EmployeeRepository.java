package springboot.APIFramework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.APIFramework.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    @Query("FROM Employee ORDER BY salary Asc")
    List<Employee> findAllOrderBySalaryAsc();
    @Query("SELECT e fROM Employee e WHERE e.department = :department  ORDER BY e.id ASC")
    List<Employee> findByDepartment(String department);

}