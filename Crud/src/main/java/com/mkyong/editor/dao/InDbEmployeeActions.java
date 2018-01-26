package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;
import org.primefaces.model.SortOrder;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InDbEmployeeActions implements EmployeeActions {


    private DataSource dataSource;

    public InDbEmployeeActions() {
        try {
            Context initCtx = new InitialContext();
            this.dataSource = (DataSource) initCtx.lookup("java:/comp/env/jdbc/EmployeesDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    private Connection getConnection() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb?useSSL=false", "root", "root");
//            Statement stmtObj = conn.createStatement();
//
//        } catch (Exception sqlException) {
//            sqlException.printStackTrace();
//        }
//        return conn;
//
//    }

    @Override
    public String deleteEmployeeRecords(long employeeId) {

        System.out.println("deleteEmployeeRecordInDB() : Student Id: " + employeeId);
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("delete from employeetable where ID = " + employeeId)) {

            pstmt.executeUpdate();

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewEmployee(Employee newEmployee) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(newEmployee.getDate());

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO employeetable (Name, Position, Department, Date) VALUES (?, ?, ?, ?)")) {

            pstmt.setString(1, newEmployee.getName());
            pstmt.setString(2, newEmployee.getPosition());
            pstmt.setString(3, newEmployee.getDepartment());
            pstmt.setDate(4, java.sql.Date.valueOf(date));
            pstmt.executeUpdate();

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void updateEmployeeRecords(Employee updateEmployee) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(updateEmployee.getDate());
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("UPDATE employeetable SET Name=?, Position=?, Department=?, Date=? WHERE id=?")) {

            pstmt.setString(1, updateEmployee.getName());
            pstmt.setString(2, updateEmployee.getPosition());
            pstmt.setString(3, updateEmployee.getDepartment());
            pstmt.setDate(4, java.sql.Date.valueOf(date));
            pstmt.setLong(5, updateEmployee.getId());


            pstmt.executeUpdate();

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    // TODO: 2018-01-24 kodel exucutina 2 kartus
    public int getTotalNumberOfEmployees() {
        String query = "SELECT COUNT(*) FROM employeetable";
        int rowCount = 0;

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            rowCount = rs.getInt(1);
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return rowCount;


    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM employeetable")) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("ID"));
                emp.setName(rs.getString("Name"));
                emp.setPosition(rs.getString("Position"));
                emp.setDepartment(rs.getString("Department"));

                employeeList.add(emp);
            }
            System.out.println("Total Records Fetched: " + employeeList.size());


        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return employeeList;


    }

    @Override
    public List<Employee> getSelectedEmployees(int first, int pageSize, String sortField, SortOrder sortOrder) {
//             if (sortField == ("name")) {
//            String sortName = "ASC";
//            if (sortOrder.name().equals("ASCENDING")) {
//                sortName = "ASC";
//            } else if (sortOrder.name().equals("DESCENDING")) {
//                sortName = "DESC";
//            }
//

//    }
        List<Employee> employeeList = new ArrayList<>();
        //    String query = String.format("SELECT * FROM employeetable ORDER BY %1s %2s", sortField, sortName);
        String query = String.format("SELECT * FROM employeetable LIMIT %1s, %2s", first, first + pageSize);

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("ID"));
                emp.setName(rs.getString("Name"));
                emp.setPosition(rs.getString("Position"));
                emp.setDepartment(rs.getString("Department"));
                emp.setDate(rs.getDate("Date"));




                employeeList.add(emp);
            }
            System.out.println("Total Records Fetched: " + employeeList.size());
            return employeeList;
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return employeeList;
    }


    @Override
    public Employee editEmployeeRecords(long employeeId) {
        Employee editRecord = null;
        try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement("select * from employeetable where ID = " + employeeId)) {
            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                rs.next();
                editRecord = new Employee();
                editRecord.setId((int) rs.getLong("id"));
                editRecord.setName(rs.getString("Name"));
                editRecord.setPosition(rs.getString("Position"));
                editRecord.setDepartment(rs.getString("Department"));
                editRecord.setDate(rs.getDate("Date"));
            }

            return editRecord;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}




