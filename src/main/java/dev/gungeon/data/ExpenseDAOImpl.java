package dev.gungeon.data;

import dev.gungeon.entities.Expense;

import java.util.ArrayList;

public class ExpenseDAOImpl implements ExpenseDAO {

    @Override
    public Expense createExpense(Expense expense) {
        return null;
    }

    @Override
    public Expense getExpense(int id) {
        return null;
    }

    @Override
    public boolean putExpense(Expense expense) {
        return false;
    }

    @Override
    public boolean respondExpense(int id, boolean approved) {
        return false;
    }

    @Override
    public boolean deleteExpense(int id) {
        return false;
    }

    @Override
    public ArrayList<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public ArrayList<Expense> getExpensesByEmployee(int empID) {
        return null;
    }
}
