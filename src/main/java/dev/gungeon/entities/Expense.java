package dev.gungeon.entities;

public class Expense {
    int id;
    int empID;
    double amount;
    //-1 pending, 0 denied, 1 approved
    int status;

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
