package dev.gungeon.tests;

import dev.gungeon.data.*;
import dev.gungeon.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseTest {

    @Test
    void EmployeeTest() {
        EmployeeDAO empdao = new EmployeeDAOImpl();
        Employee e = empdao.createEmployee(new Employee("Machine","Girl"));
        Assertions.assertNotEquals(0, e.getId());
        empdao.deleteEmployee(e);
    }

    @Test
    void ExpenseTest() throws Exception {
        throw new Exception("Unwritten");
    }
}
