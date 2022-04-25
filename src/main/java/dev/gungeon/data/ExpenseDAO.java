package dev.gungeon.data;

import dev.gungeon.entities.Expense;
import dev.gungeon.utilities.exceptions.ConfirmedExpenseException;

import java.util.ArrayList;

public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense getExpense(int id);

    boolean putExpense(Expense expense) throws ConfirmedExpenseException;

    boolean respondExpense(Expense expense, boolean approved) throws ConfirmedExpenseException;

    boolean deleteExpense(Expense Expense) throws ConfirmedExpenseException;

    ArrayList<Expense> getAllExpenses();

    ArrayList<Expense> getExpensesByEmployee(int empID);
}
