package pro.sky.mockito.service;
import pro.sky.mockito.model.Employee;

import java.math.BigDecimal;
import java.util.Collection;

public interface EmployeeService {

    Employee addEmployee(String name, Integer department, BigDecimal salary);

    boolean removeEmployee(String name);

    Employee findEmployee(String name);

    Collection<Employee> getAllEmployees();
}
