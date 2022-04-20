package dev.gungeon.data;

import dev.gungeon.entities.Employee;
import dev.gungeon.utilities.ConnectionUtil;

import java.sql.*;

import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public Employee createEmployee(Employee employee) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into employees values(default,?,?)";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("id");
            employee.setId(generatedId);
            return employee;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update employees set firstname = ?, lastname = ? where id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3,employee.getId());

            ps.executeUpdate();
            return employee;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployee(int id) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from employees where id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if(!rs.next())
                return null;
            return new Employee(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname"));

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployee(int id) {
        try {
            if(getEmployee(id) == null)
                return false;
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update expenses set emp_id = ? where emp_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setNull(1,java.sql.Types.NULL);
            ps.setInt(2,id);
            sql = "delete from employees where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(Employee emp) {
        return deleteEmployee(emp.getId());
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from employees");
            ResultSet rs = ps.executeQuery();

            ArrayList<Employee> list = new ArrayList<>();
            while(rs.next()) {
                list.add(new Employee(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname")));
            }

            return list;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
