package dev.gungeon.data;

import dev.gungeon.entities.Employee;
import dev.gungeon.entities.Expense;

import java.util.ArrayList;

public interface EmployeeDAO {

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee getEmployee(int id);

    boolean deleteEmployee(int id);
    boolean deleteEmployee(Employee emp);

    ArrayList<Employee> getAllEmployees();
}