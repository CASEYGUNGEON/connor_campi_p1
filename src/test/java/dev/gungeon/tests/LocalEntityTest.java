package dev.gungeon.tests;
import dev.gungeon.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalEntityTest {

    @Test
    void EmployeeTest() {
        Employee e = new Employee(1,"John","Doe");
        e.setId(2);
        Assertions.assertEquals(2, e.getId());
        e.setFirstName("Doctor");
        Assertions.assertEquals("Doctor", e.getFirstName());
        e.setLastName("Freeman");
        Assertions.assertEquals("Freeman", e.getLastName());
    }

    @Test
    void ExpenseTest() {
        Expense e = new Expense(1,1000);
        e.setStatus(1);
        Assertions.assertEquals(1,e.getStatus());
    }
}