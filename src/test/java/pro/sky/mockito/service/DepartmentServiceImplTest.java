package pro.sky.mockito.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.mockito.exceptions.EmployeeNotFoundException;
import pro.sky.mockito.model.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentServiceImpl departmentService;

    Employee e1;
    Employee e2;
    Employee e3;
    List<Employee> employees;
    Map<Integer, List<Employee>> employeesByDepartment;

    @BeforeEach
    public void setUp() {
        e1 = new Employee("Иван", 1, BigDecimal.valueOf(1000));
        e2 = new Employee("Сергей", 1, BigDecimal.valueOf(2000));
        e3 = new Employee("Алексей", 2, BigDecimal.valueOf(3000));
        employees = List.of(e1, e3, e2);
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
        employeesByDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }


    @Test
    void getMinSalary() {
        BigDecimal actual = departmentService.getMinSalary(1);
        assertEquals(BigDecimal.valueOf(1000), actual);
    }

    @Test
    void getMinSalaryShouldThrowEmployeeNotFoundExceptionWhenArgumentIsWrongOrDoesNotExist() {
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getMinSalary(-1));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getMinSalary(null));
    }

    @Test
    void getMaxSalary() {
        BigDecimal actual = departmentService.getMaxSalary(1);
        assertEquals(BigDecimal.valueOf(2000), actual);
    }

    @Test
    void getMaxSalaryShouldThrowEmployeeNotFoundExceptionWhenArgumentIsWrongOrDoesNotExist() {
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getMaxSalary(-1));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getMaxSalary(null));
    }

    @Test
    void getSumSalary() {
        BigDecimal actual = departmentService.getSumSalary(1);
        assertEquals(BigDecimal.valueOf(3000), actual);
    }

    @Test
    void getSumSalaryShouldThrowNothingWhenArgumentIsWrongOrDoesNotExist() {
        assertDoesNotThrow(() -> departmentService.getSumSalary(-1));
        assertDoesNotThrow(() -> departmentService.getSumSalary(null));
    }

    @Test
    void getEmployeesOfDepartment() {
        //given
        Employee  e1 = new Employee("Иван", 1, BigDecimal.valueOf(1000));
        Employee  e2 = new Employee("Сергей", 1, BigDecimal.valueOf(2000));
        List<Employee> expected = List.of(e1, e2);
        //when
        List<Employee> actual = departmentService.getEmployeesOfDepartment(1);
        //then
        assertIterableEquals(expected, actual);
        assertEquals(2, actual.size());
        assertTrue(employeesByDepartment.get(1).containsAll(actual));
    }

    @Test
    void getEmployeesOfDepartmentListShouldBeEmptyAndThrowsNothingWhenArgumentIsWrongOrDoesNotExist() {
        //given
        List<Employee> expected;
        expected = List.of();
        //when
        List<Employee> actual = departmentService.getEmployeesOfDepartment(-1);
        //then
        assertIterableEquals(expected, actual);
        assertEquals(0, actual.size());
        assertTrue(employeesByDepartment.get(1).containsAll(actual));
        assertDoesNotThrow(() -> departmentService.getEmployeesOfDepartment(-1));
    }

    @Test
    void groupAllEmployeesByDepartments() {
        Map<Integer, List<Employee>> expected = departmentService.groupAllEmployeesByDepartments();
        assertEquals(expected, employeesByDepartment);
    }

    @Test
    void groupAllEmployeesByDepartmentsThrowsNothingWhenListIsEmpty() {
        List<Employee> employeesEmpty = List.of();
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employeesEmpty);
        Map<Integer, List<Employee>> employeesByDepartmentEmpty = employeesEmpty.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        Map<Integer, List<Employee>> expected = departmentService.groupAllEmployeesByDepartments();
        assertEquals(expected, employeesByDepartmentEmpty);
        assertDoesNotThrow(() -> departmentService.groupAllEmployeesByDepartments());
    }
}