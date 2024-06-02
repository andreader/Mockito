package pro.sky.mockito.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.mockito.model.Employee;
import pro.sky.mockito.service.DepartmentService;
import pro.sky.mockito.service.EmployeeService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/max-salary")
    public BigDecimal getEmployeeWithMaxSalary(Integer departmentId) {
        return departmentService.getMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public BigDecimal getEmployeeWithMinSalary(Integer departmentId) {
        return departmentService.getMinSalary(departmentId);
    }

    @GetMapping("/all-by-departments")
    public Map<Integer, List<Employee>> groupAllEmployeesByDepartments() {
        return departmentService.groupAllEmployeesByDepartments();
    }

    @GetMapping("/department/{id}/salary/sum")
    public BigDecimal getSumSalary(@PathVariable Integer id) {
        return departmentService.getSumSalary(id);
    }

    @GetMapping("/employees-department")
    public List<Employee> getEmployeesOfDepartment(Integer departmentId) {
        return departmentService.getEmployeesOfDepartment(departmentId);
    }

}
