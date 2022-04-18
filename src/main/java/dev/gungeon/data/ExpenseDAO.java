package dev.gungeon.data;

import dev.gungeon.entities.ExpenseInterface;

public interface ExpenseDAO {

    ExpenseInterface CreateExpense(ExpenseInterface expense);

    ExpenseInterface GetExpense(int id);

    boolean PutExpense(ExpenseInterface expense);

    boolean RespondExpense(int id, boolean approved);

    boolean DeleteExpense(int id);

    ExpenseInterface[] GetAllExpenses();

    ExpenseInterface[] GetExpensesByEmployee(int empID);
}
