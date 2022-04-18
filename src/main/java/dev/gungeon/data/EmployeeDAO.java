package dev.gungeon.data;

import dev.gungeon.entities.Employee;
import dev.gungeon.entities.Expense;

import java.util.List;

public interface EmployeeDAO {

    Employee CreateEmployee(Employee employee);

    Employee GetEmployee(int id);

    boolean DeleteEmployee(int id);

    List<Employee> GetAllEmployees();
}
