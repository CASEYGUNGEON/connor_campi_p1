package dev.gungeon.entities;

public class Expense {
    private int id;
    private int empID;
    private double amount;
    //-1 pending, 0 denied, 1 approved
    private int status;

    public Expense(int i) {
        id = i;
    }

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

    public void setId(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }

    public void setEmployee(int i) {
        empID = i;
    }

    public int getEmployee() {
        return empID;
    }

    public void setAmount(double amt) {
        amount = amt;
    }

    public double getAmount() {
        return amount;
    }

    public void setStatus(int sta) {
        status = sta;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        String out = this.id + ": Employee: " + this.empID + ", $" + String.format("%.2f",this.amount) + ", ";
        switch(this.status) {
            case 1: {
                out += "Approved";
            } break;
            case 0: {
                out += "Denied";
            } break;
            default: {
                out += "Pending";
            }
        }
        return out;
    }
}
