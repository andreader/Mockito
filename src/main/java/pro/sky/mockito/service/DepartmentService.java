package pro.sky.mockito.service;

import pro.sky.mockito.model.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    BigDecimal getMinSalary(Integer departmentId);

    BigDecimal getMaxSalary(Integer departmentId);

    BigDecimal getSumSalary(Integer departmentId);

    List<Employee> getEmployeesOfDepartment(Integer departmentId);

    Map<Integer, List<Employee>> groupAllEmployeesByDepartments();
}
