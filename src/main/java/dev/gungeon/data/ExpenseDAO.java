package dev.gungeon.data;

import dev.gungeon.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    Expense CreateExpense(Expense expense);

    Expense GetExpense(int id);

    boolean PutExpense(Expense expense);

    boolean RespondExpense(int id, boolean approved);

    boolean DeleteExpense(int id);

    List<Expense> GetAllExpenses();

    List<Expense> GetExpensesByEmployee(int empID);
}
