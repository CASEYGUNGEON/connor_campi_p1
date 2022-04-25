package dev.gungeon.tests;

import dev.gungeon.data.*;
import dev.gungeon.entities.*;
import dev.gungeon.utilities.exceptions.ConfirmedExpenseException;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class DatabaseTest {
    EmployeeDAO empdao = new EmployeeDAOImpl();
    ExpenseDAO expdao = new ExpenseDAOImpl();
    Employee e = new Employee("Machine", "Girl");
    Expense ex = new Expense(6,2.00);

    @Test
    @Order(1)
    void MakeEmployeeTest() {
        e = empdao.createEmployee(e);
        Assertions.assertNotEquals(0, e.getId());
    }

    @Test
    @Order(2)
    void EditEmployeeTest() {
        assert e != null;
        Employee f = new Employee(e.getId(),e.getFirstName(),e.getLastName());
        f.setFirstName("Xanthe");
        f.setLastName("Twosuns");
        f = empdao.updateEmployee(f);
        Assertions.assertNotEquals(e.getFirstName(),f.getFirstName());
    }

    @Test
    @Order(3)
    void DeleteEmployeeTest() {
        empdao.deleteEmployee(e);
    }

    @Test
    @Order(4)
    void MakeExpenseTest() {
        ex = expdao.createExpense(ex);
        Assertions.assertNotEquals(0,ex.getId());
    }

    @Test
    @Order(5)
    void EditExpenseTest() {

    }

    @Test
    @Order(6)
    void DeleteExpenseTest() throws ConfirmedExpenseException {
        expdao.deleteExpense(ex);
    }
}
