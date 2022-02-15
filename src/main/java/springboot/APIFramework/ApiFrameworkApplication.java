package springboot.APIFramework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springboot.APIFramework.entities.Employee;
import springboot.APIFramework.repository.EmployeeRepository;

@SpringBootApplication
public class ApiFrameworkApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiFrameworkApplication.class, args);
	}
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setName("Naveen");
		employee.setSalary("50000");
		employee.setDepartment("Software");
		employeeRepository.save(employee);
	}
}
