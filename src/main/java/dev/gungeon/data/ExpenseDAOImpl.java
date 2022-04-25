package dev.gungeon.data;

import dev.gungeon.entities.Employee;
import dev.gungeon.entities.Expense;
import dev.gungeon.utilities.ConnectionUtil;
import dev.gungeon.utilities.exceptions.ConfirmedExpenseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ExpenseDAOImpl implements ExpenseDAO {

    @Override
    public Expense createExpense(Expense expense) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into expenses values(?,?,?,default)";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expense.getEmployee());
            ps.setDouble(2, expense.getAmount());
            switch(expense.getStatus()) {
                case 1: {
                    ps.setBoolean(3, true);
                }    break;
                case 0: {
                    ps.setBoolean(3, false);
                }    break;
                default: {
                    ps.setNull(3, java.sql.Types.NULL);
                }    break;
            }

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("id");
            expense.setId(generatedId);
            return expense;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpense(int id) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from employees where id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if(!rs.next())
                return null;
            return new Expense(rs.getInt("id"),rs.getInt("emp_id"),rs.getDouble("amount"));

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean putExpense(Expense expense) throws ConfirmedExpenseException, NoSuchElementException {
        if(expense.getStatus() != -1) {
            throw new ConfirmedExpenseException("Expense Already confirmed.");
        }
        if(getExpense(expense.getId()) == null) {
            throw new NoSuchElementException("Not found.");
        }
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update expense set emp_id = ?, amount = ?, status = ?, where id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expense.getEmployee());
            ps.setDouble(2, expense.getAmount());
            switch(expense.getStatus()) {
                case 1: {
                    ps.setBoolean(3, true);
                }    break;
                case 0: {
                    ps.setBoolean(3, false);
                }    break;
                default: {
                    ps.setNull(3, java.sql.Types.NULL);
                }    break;
            }

            ps.execute();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean respondExpense(Expense expense, boolean approved) throws ConfirmedExpenseException {
        if(expense.getStatus() == -1) {
            try {
                Connection conn = ConnectionUtil.createConnection();
                String sql = "update expenses set status = ? where id = ?";
                assert conn != null;
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setBoolean(1, (expense.getStatus() == 1));
                ps.setInt(2, expense.getId());
                ps.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new ConfirmedExpenseException("Expense already responded to.");
    }

    @Override
    public boolean deleteExpense(Expense expense) throws ConfirmedExpenseException {
        if(getExpense(expense.getId()).getStatus() != 1)
            throw new ConfirmedExpenseException("Expense already confirmed");
        if(getExpense(expense.getId()) == null)
            throw new NoSuchElementException("Expense not found.");
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from expenses where id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expense.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteExpense(int id) throws ConfirmedExpenseException {
        Expense ex = new Expense(id);
        return deleteExpense(ex);
    }

    @Override
    public ArrayList<Expense> getAllExpenses() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from expenses");
            ResultSet rs = ps.executeQuery();

            ArrayList<Expense> list = new ArrayList<>();
            while(rs.next()) {
                Expense e = new Expense(rs.getInt("id"),rs.getInt("emp_id"),rs.getDouble("amount"),rs.getBoolean("status") ? 1 : 0);
                if(rs.wasNull())
                    e.setStatus(-1);
                list.add(e);
            }
            return list;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Expense> getExpensesByEmployee(int empID) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from expenses where emp_id = ?");
            ps.setInt(1,empID);
            ResultSet rs = ps.executeQuery();

            ArrayList<Expense> list = new ArrayList<>();
            while(rs.next()) {
                Expense e = new Expense(rs.getInt("id"),empID,rs.getDouble("amount"),rs.getBoolean("status") ? 1 : 0);
                if(rs.wasNull())
                    e.setStatus(-1);
                list.add(e);
            }
            return list;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
