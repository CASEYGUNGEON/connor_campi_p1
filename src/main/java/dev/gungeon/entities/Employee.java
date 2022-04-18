package dev.gungeon.entities;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;

    public Employee(String f, String l) {
        firstName = f;
        lastName = l;
    }

    public Employee(int i, String f, String l) {
        id = i;
        firstName = f;
        lastName = l;
    }
}
