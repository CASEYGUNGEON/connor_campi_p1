package dev.gungeon.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.gungeon.data.EmployeeDAOImpl;
import dev.gungeon.data.ExpenseDAOImpl;
import dev.gungeon.entities.Employee;
import dev.gungeon.entities.Expense;
import dev.gungeon.utilities.exceptions.ConfirmedExpenseException;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create();
        EmployeeDAOImpl empdao = new EmployeeDAOImpl();
        ExpenseDAOImpl expdao = new ExpenseDAOImpl();
        Gson gson = new GsonBuilder().create();

        app.delete("/employees/{num}", context -> {
            int id = Integer.parseInt(context.pathParam("num"));
            boolean success = empdao.deleteEmployee(id);
            if(success) {
                context.result("Employee " + id + " deleted.");
                context.status(200);
            }
            else {
                context.result("Employee record not found.");
                context.status(404);
            }
        });

        app.get("/employees", context -> {
            ArrayList<Employee> list = empdao.getAllEmployees();
            StringBuilder out = new StringBuilder("Employees:\n");
            for(Employee e : list) {
                out.append(e.toString());
                out.append("\n");
            }
            context.status(200);
            context.result(String.valueOf(out));
        });

        app.get("/employees/{num}", context -> {
            int id = Integer.parseInt(context.pathParam("num"));
            Employee e = empdao.getEmployee(id);
            if(e != null) {
                context.result(e.toString());
                context.status(200);
            }
            else {
                context.result("Employee record not found.");
                context.status(404);
            }
        });

        app.post("/employees", context -> {
            Employee e = gson.<Employee>fromJson(context.body(), Employee.class);
            if(e!=null) {
                empdao.createEmployee(e);
                context.result("Employee record successfully added.");
                context.status(201);
            }
            else {
                context.result("Failed.\n" + context.body());
                context.status(500);
            }
        });

        app.put("/employees/{num}", context -> {
            int id = Integer.parseInt(context.pathParam("num"));
            Employee e = gson.<Employee>fromJson(context.body(), Employee.class);
            e.setId(id);
            e = empdao.updateEmployee(e);

            if(e == null) {
                context.result("Employee record not found.");
                context.status(404);
            }
            else {
                context.result("Employee record updated.");
                context.status(200);
            }
        });


        //Expenses

        app.post("/expenses", context -> {
            Expense e = gson.<Expense>fromJson(context.body(), Expense.class);
            if(e != null) {
                e = expdao.createExpense(e);
                if(e != null) {
                    context.result("Expense report added.");
                    context.status(201);
                    return;
                }
            }
            context.result("Failed.\n" + context.body());
            context.status(500);
        });

        app.get("/expenses", context -> {
            ArrayList<Expense> list = expdao.getAllExpenses();
            StringBuilder out = new StringBuilder("Expenses:\n");
            for (Expense e : list) {
                out.append(e.toString());
                out.append("\n");
            }
            context.status(200);
            context.result(String.valueOf(out));
        });

        app.get("/expenses/{num}", context -> {
            String q = context.queryParam("status");
            int id = Integer.parseInt(context.pathParam("num"));
            Expense e = expdao.getExpense(id);
            if(q != null) {
                context.result("False");
                context.status(200);
                switch(e.getStatus()) {
                    case 1: {
                        if(q.equals("approved"))
                            context.result("True");
                    } break;
                    case 0: {
                        if(q.equals("denied"))
                            context.result("True");
                    } break;
                    default: {
                        if(q.equals("pending"))
                            context.result("True");
                    } break;
                }
            }
            else {
                if(e != null) {
                    context.result(e.toString());
                    context.status(200);
                }
                else {
                    context.result("Not found.");
                    context.status(404);
                }
            }
        });

        app.put("/expenses/{num}", context -> {
            Expense ex = gson.<Expense>fromJson(context.body(), Expense.class);
            try {
                expdao.putExpense(ex);
            }
            catch (ConfirmedExpenseException e) {
                context.result("Cannot modify expense.");
                context.status(500);
            }
            catch(NoSuchElementException e) {
                context.result("Expense not found.");
                context.status(404);
            }

        });

        app.patch("/expenses/{num}/{status}", context -> {
            int id = Integer.parseInt(context.pathParam("num"));
            String status = context.pathParam("status");
        });

        app.delete("/expenses/{num}", context -> {
            try {
                expdao.deleteExpense(Integer.parseInt(context.pathParam("num")));
            }
            catch(ConfirmedExpenseException e) {
                context.result("Expense already confirmed. Cannot remove.");
                context.status(500);
            }
            catch(NoSuchElementException e) {
                context.result("Expense not found.");
                context.status(404);
            }
        });

        app.start(7000);
    }
}