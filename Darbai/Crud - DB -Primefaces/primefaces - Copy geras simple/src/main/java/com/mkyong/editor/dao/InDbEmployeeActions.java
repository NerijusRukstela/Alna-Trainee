package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;

import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InDbEmployeeActions implements EmployeeActions {
    private Statement stmtObj;
    private Connection connObj;
    private ResultSet resultSetObj;
    private PreparedStatement pstmt;


    private Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connObj = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "root");
            stmtObj = connObj.createStatement();

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return connObj;

    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employeeList = new ArrayList<>();
        try {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("SELECT * FROM employeetable");
            while (resultSetObj.next()) {
                Employee stuObj = new Employee();
                stuObj.setId(resultSetObj.getInt("ID"));
                stuObj.setName(resultSetObj.getString("Name"));
                stuObj.setPosition(resultSetObj.getString("Position"));
                stuObj.setDepartment(resultSetObj.getString("Department"));
                employeeList.add(stuObj);
            }
            System.out.println("Total Records Fetched: " + employeeList.size());
            stmtObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void addNewEmployee(Employee newStudentObj) {

        try {
            pstmt = getConnection().prepareStatement("INSERT INTO employeetable (Name, Position, Department) VALUES (?, ?, ?)");
            pstmt.setString(1, newStudentObj.getName());
            pstmt.setString(2, newStudentObj.getPosition());
            pstmt.setString(3, newStudentObj.getDepartment());
            pstmt.executeUpdate();
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void updateEmployeeRecords(Employee updateStudentObj) {

        try {

            pstmt = getConnection().prepareStatement("UPDATE employeetable SET Name=?, Position=?, Department=? WHERE id=?");
            pstmt.setString(1, updateStudentObj.getName());
            pstmt.setString(2, updateStudentObj.getPosition());
            pstmt.setString(3, updateStudentObj.getDepartment());
            pstmt.setLong(4, updateStudentObj.getId());
            pstmt.executeUpdate();
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Employee editEmployeeRecords(long employeeId) {
        Employee editRecord = null;


        try {
            // stmtObj = getConnection().createStatement();
            String sql = "select * from employeetable where ID = " + employeeId ;
            PreparedStatement ps = getConnection().prepareStatement(sql);

            //resultSetObj = getConnection().createStatement().executeQuery("select * from employeetable where ID = " + employeeId);

            if (resultSetObj != null) {
                resultSetObj.next();
                editRecord = new Employee();
                editRecord.setId((int) resultSetObj.getLong("id"));
                editRecord.setName(resultSetObj.getString("Name"));
                editRecord.setPosition(resultSetObj.getString("Position"));
                editRecord.setDepartment(resultSetObj.getString("Department"));

            }
            connObj.close();
            return editRecord;

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteEmployeeRecords(long employeeId) {

        try {
            pstmt = getConnection().prepareStatement("delete from employeetable where ID = " + employeeId);
            pstmt.executeUpdate();
            connObj.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }


}
