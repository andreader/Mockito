package pro.sky.mockito.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.sky.mockito.model.Employee;
import pro.sky.mockito.service.EmployeeService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/add-employee")
    public Employee addEmployee(@RequestParam String name, @RequestParam Integer department, @RequestParam BigDecimal salary) {
        return employeeService.addEmployee(name, department, salary);
    }

    @DeleteMapping("/remove-employee")
    public boolean removeEmployee(@RequestParam String name) {
        return employeeService.removeEmployee(name);
    }

    @GetMapping("/find-employee")
    public Employee findEmployee(@RequestParam String name) {
        return employeeService.findEmployee(name);
    }

    @GetMapping("/all-employees")
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}