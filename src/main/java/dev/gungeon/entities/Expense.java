package dev.gungeon.entities;

public class Expense implements ExpenseInterface {
    int id;
    int empID;
    double amount;

    public Expense(int emp, double amt) {
        empID = emp;
        amount = amt;
    }

    public Expense(int i, int emp, double amt) {
        id = i;
        empID = emp;
        amount = amt;
    }
}
