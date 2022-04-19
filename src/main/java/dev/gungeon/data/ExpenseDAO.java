package dev.gungeon.data;

import dev.gungeon.entities.Expense;

import java.util.ArrayList;

public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense getExpense(int id);

    boolean putExpense(Expense expense);

    boolean respondExpense(int id, boolean approved);

    boolean deleteExpense(int id);

    ArrayList<Expense> getAllExpenses();

    ArrayList<Expense> getExpensesByEmployee(int empID);
}
