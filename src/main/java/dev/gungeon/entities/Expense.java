package dev.gungeon.entities;

public class Expense {
    private int id;
    private int empID;
    private double amount;
    //-1 pending, 0 denied, 1 approved
    private int status;

    public Expense(int emp, double amt) {
        empID = emp;
        amount = amt;
        status = -1;
    }

    public Expense(int i, int emp, double amt) {
        id = i;
        empID = emp;
        amount = amt;
        status = -1;
    }

    public Expense(int i, int emp, double amt, int sta) {
        id = i;
        empID = emp;
        amount = amt;
        status = sta;
    }
}
