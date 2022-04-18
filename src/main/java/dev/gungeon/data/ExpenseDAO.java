package dev.gungeon.data;

import dev.gungeon.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense getExpense(int id);

    boolean putExpense(Expense expense);

    boolean respondExpense(int id, boolean approved);

    boolean deleteExpense(int id);

    List<Expense> getAllExpenses();

    List<Expense> getExpensesByEmployee(int empID);
}
