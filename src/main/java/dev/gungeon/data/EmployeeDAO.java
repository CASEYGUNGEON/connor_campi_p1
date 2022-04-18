package dev.gungeon.data;

import dev.gungeon.entities.Employee;
import dev.gungeon.entities.Expense;

import java.util.List;

public interface EmployeeDAO {

    Employee createEmployee(Employee employee);

    Employee getEmployee(int id);

    boolean deleteEmployee(int id);

    List<Employee> getAllEmployees();
}
