package ru.cereevse.kyrs.servive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cereevse.kyrs.exceptions.ExceptionHandler;
import ru.cereevse.kyrs.model.Employee;
import ru.cereevse.kyrs.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    private final ExceptionHandler exceptionHandler;

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> putEmployeeById(Long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if(existingEmployee.isPresent()){
            Employee employeeToUpdate = existingEmployee.get();

            if(updatedEmployee.getNameEmployee() != null) {
                employeeToUpdate.setNameEmployee(updatedEmployee.getNameEmployee());
            }
            if(updatedEmployee.getSurnameEmployee() != null) {
                employeeToUpdate.setSurnameEmployee(updatedEmployee.getSurnameEmployee());
            }
            if (updatedEmployee.getTelNumberEmployee() != null){
                employeeToUpdate.setTelNumberEmployee(updatedEmployee.getTelNumberEmployee());
            }
            employeeRepository.save(employeeToUpdate);
        }
        return existingEmployee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
