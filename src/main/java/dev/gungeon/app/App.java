package dev.gungeon.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.gungeon.data.EmployeeDAOImpl;
import dev.gungeon.entities.Employee;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create();
        EmployeeDAOImpl empdao = new EmployeeDAOImpl();
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




        app.start(7000);
    }
}