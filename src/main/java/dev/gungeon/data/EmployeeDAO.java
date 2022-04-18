package dev.gungeon.data;

import dev.gungeon.entities.EmployeeInterface;

public interface EmployeeDAO {

    EmployeeInterface CreateEmployee(EmployeeInterface employee);

    EmployeeInterface GetEmployee(int id);

    boolean DeleteEmployee(int id);

    EmployeeInterface[] GetAllEmployees();
}
