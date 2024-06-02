package pro.sky.mockito.service;
import org.junit.jupiter.api.Test;
import pro.sky.mockito.exceptions.ArrayIsFullException;
import pro.sky.mockito.exceptions.EmployeeAlreadyAddedException;
import pro.sky.mockito.model.Employee;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceListImplTest {
    EmployeeService employeeService = new EmployeeServiceListImpl();

    @Test
    void shouldAssertEmployeesAndArraySizeWhenWeAddEmployee() {
        //given
        Employee expected = new Employee("Иван", 1, BigDecimal.valueOf (1000));
        //when
        Employee actual = employeeService.addEmployee("Иван", 1, BigDecimal.valueOf (1000));
        //then
        assertEquals(expected, actual);
        assertEquals(1, employeeService.getAllEmployees().size());
    }

    @Test
    void shouldThrowEmployeeAlreadyAddedExceptionWhenAddEmployee() {
        //given
        //when
        employeeService.addEmployee("Иван", 1, BigDecimal.valueOf (1000));
        //then
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee("Иван", 1, BigDecimal.valueOf (1000)));
    }

    @Test
    void shouldThrowArrayIsFullExceptionWhenAddEmployee() {
        //given
        //when
        employeeService.addEmployee("Иван", 1, BigDecimal.valueOf (1000));
        employeeService.addEmployee("Сергей", 1, BigDecimal.valueOf (2000));
        employeeService.addEmployee("Алексей", 2, BigDecimal.valueOf (3000));
        //then
        assertThrows(ArrayIsFullException.class, () -> employeeService.addEmployee("Михаил", 1, BigDecimal.valueOf (1000)));
    }

    @Test
    void shouldFindEmployeeWhenTheyExist() {
        //given
        Employee expected = employeeService.addEmployee("Иван", 1, BigDecimal.valueOf (1000));
        //when
        Employee actual = employeeService.findEmployee("Иван");
        //then
        assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveEmployeeWhenTheyExist() {
        //given
        Employee expected = employeeService.addEmployee("Иван", 1, BigDecimal.valueOf (1000));
        //when
        Employee actual = employeeService.findEmployee("Иван");
        //then
        assertEquals(1, employeeService.getAllEmployees().size());
        assertTrue(employeeService.getAllEmployees().contains(expected));
        assertEquals(expected, actual);
        employeeService.removeEmployee("Иван");
        assertTrue(employeeService.getAllEmployees().isEmpty());
    }


    @Test
    void getAllEmployees() {
        //given
        Employee e1 =  employeeService.addEmployee("Иван", 1, BigDecimal.valueOf (1000));
        Employee e2 =  employeeService.addEmployee("Сергей", 1, BigDecimal.valueOf (2000));
        Employee e3 =  employeeService.addEmployee("Алексей", 2, BigDecimal.valueOf (3000));
        Collection<Employee> expected = List.of(e1, e2, e3);
        //when
        Collection<Employee> actual = employeeService.getAllEmployees();
        //then
        assertIterableEquals(expected, actual);
        assertTrue(expected.containsAll(actual));
    }
}