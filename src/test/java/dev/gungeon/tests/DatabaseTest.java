package dev.gungeon.tests;

import dev.gungeon.data.*;
import dev.gungeon.entities.*;
import dev.gungeon.utilities.exceptions.ConfirmedExpenseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseTest {
    EmployeeDAO empdao = new EmployeeDAOImpl();
    ExpenseDAO expdao = new ExpenseDAOImpl();

    @Test
    void EmployeeTest() {
        Employee e = empdao.createEmployee(new Employee("Machine","Girl"));
        Assertions.assertNotEquals(0, e.getId());
        empdao.deleteEmployee(e);
    }

    @Test
    void ExpenseTest() throws ConfirmedExpenseException {
        Expense e = new Expense(6,2.00);
        e = expdao.createExpense(e);
        Assertions.assertNotEquals(0,e.getId());
        expdao.deleteExpense(e);
    }
}
