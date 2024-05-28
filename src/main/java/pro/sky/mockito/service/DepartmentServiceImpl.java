package pro.sky.mockito.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pro.sky.mockito.exceptions.EmployeeNotFoundException;
import pro.sky.mockito.model.Employee;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public BigDecimal getMinSalary(Integer departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter((employee) -> employee.getDepartment().equals(departmentId))
                .map(Employee::getSalary)
                .sorted()
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found", HttpStatus.BAD_REQUEST));
    }

    @Override
    public BigDecimal getMaxSalary(Integer departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter((employee) -> employee.getDepartment().equals(departmentId))
                .map(Employee::getSalary)
                .max(Comparator.comparing((salary) -> salary))
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found", HttpStatus.BAD_REQUEST));
    }

    @Override
    public BigDecimal getSumSalary(Integer departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter((employee) -> employee.getDepartment().equals(departmentId))
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Employee> getEmployeesOfDepartment(Integer departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment().equals(departmentId))
                .toList();
    }

    @Override
    public Map<Integer, List<Employee>> groupAllEmployeesByDepartments() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy((Employee::getDepartment)));
    }


}
