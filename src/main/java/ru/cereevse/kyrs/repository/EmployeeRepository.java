package ru.cereevse.kyrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.cereevse.kyrs.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, PagingAndSortingRepository<Employee, Long> {
}
