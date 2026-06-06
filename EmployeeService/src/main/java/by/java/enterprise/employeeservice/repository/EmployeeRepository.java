package by.java.enterprise.employeeservice.repository;

import by.java.enterprise.employeeservice.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsByNameFirstAndNameLast(String nameFirst, String nameLast);
}
