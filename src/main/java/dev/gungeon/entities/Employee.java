package dev.gungeon.entities;

public class Employee {
    int id;
    String firstName;
    String lastName;

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
