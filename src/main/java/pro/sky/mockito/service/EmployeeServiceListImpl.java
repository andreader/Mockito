package pro.sky.mockito.service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pro.sky.mockito.exceptions.ArrayIsFullException;
import pro.sky.mockito.exceptions.EmployeeAlreadyAddedException;
import pro.sky.mockito.model.Employee;
import pro.sky.mockito.exceptions.EmployeeNotFoundException;

import java.math.BigDecimal;
import java.util.*;

import static pro.sky.mockito.utils.Constants.EMPLOYEE_AMOUNT;

@Service
@RequiredArgsConstructor
public class EmployeeServiceListImpl implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>(EMPLOYEE_AMOUNT);


    @Override
    public Employee addEmployee(String name, Integer department, BigDecimal salary) {
        Employee newEmployee = new Employee(name, department, salary);
        getAllEmployees().stream()
                .filter(employee -> employee.equals(newEmployee))
                .findAny()
                .ifPresent(employee -> {
                    throw new EmployeeAlreadyAddedException("Employee " + name +
                            " in department " + department +
                            " with salary " + salary +
                            " is already added");
                });
        if (getAllEmployees().size() >= EMPLOYEE_AMOUNT) {
            throw new ArrayIsFullException("List of employees is full");
        }

        employees.add(newEmployee);
        return newEmployee;
    }

    @Override
    public boolean removeEmployee(String name) {
        Employee employeeToFind = findEmployee(name);
        return employees.remove(employeeToFind);
    }

    @Override
    public Employee findEmployee(String name) {
        return getAllEmployees().stream()
                .filter(employee -> employee.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + name +
                        " not found"));
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableList(employees);
    }

}



