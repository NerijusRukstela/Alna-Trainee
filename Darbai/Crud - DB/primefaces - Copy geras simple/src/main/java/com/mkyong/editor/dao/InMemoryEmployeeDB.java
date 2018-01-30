package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;
import com.mkyong.editor.faces.EmployeesBean;

import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class InMemoryEmployeeDB implements EmployeeActionsDB {
    private Statement stmtObj;
    private Connection connObj;
    private ResultSet resultSetObj;
    private PreparedStatement pstmt;

    @Override
    public Connection getConnection(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connObj = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root", "root");
            stmtObj = connObj.createStatement();

        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return connObj;

    }
    @Override
    public ArrayList getEmployeesListFromDB() {

        ArrayList employeeList = new ArrayList();
        try {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("select * from employeetable");
            while(resultSetObj.next()) {
                EmployeesBean stuObj = new EmployeesBean();
                stuObj.setId(resultSetObj.getInt("ID"));
                stuObj.setName(resultSetObj.getString("Name"));
                stuObj.setPosition(resultSetObj.getString("Position"));
                stuObj.setDepartment(resultSetObj.getString("Department"));
                employeeList.add(stuObj);
            }
            System.out.println("Total Records Fetched: " + employeeList.size());
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return employeeList;
    }
    @Override
    public String saveEmployeeDetailsInDB(EmployeesBean newStudentObj) {
        int saveResult = 0;
        String navigationResult = "";
        try {
            pstmt = getConnection().prepareStatement("insert into employeetable (Name, Position, Department) values (?, ?, ?)");
            pstmt.setString(1, newStudentObj.getName());
            pstmt.setString(2, newStudentObj.getPosition());
            pstmt.setString(3, newStudentObj.getDepartment());
            pstmt.executeUpdate();
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }


        return null;
    }
    @Override
    public EmployeesBean editEmployeeRecordInDB(long employeeId) {
        EmployeesBean editRecord = null;
        /* Setting The Particular Student Details In Session */
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("select * from employeetable where ID = "+employeeId);
            if(resultSetObj != null) {
                resultSetObj.next();
                editRecord = new EmployeesBean();
                editRecord.setId(resultSetObj.getLong("id"));
                editRecord.setName(resultSetObj.getString("Name"));
                editRecord.setPosition(resultSetObj.getString("Position"));
                editRecord.setDepartment(resultSetObj.getString("Department"));

            }
            connObj.close();
            return editRecord;

        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }


    @Override
    public String updateEmployeeDetailsInDB(Employee updateStudentObj) {

        try {
            pstmt = getConnection().prepareStatement("update employeetable set Name=?, Position=?, Department=? where id=?");
            pstmt.setString(1,updateStudentObj.getName());
            pstmt.setString(2,updateStudentObj.getPosition());
            pstmt.setString(3,updateStudentObj.getDepartment());
            pstmt.setLong(4,updateStudentObj.getId());
            pstmt.executeUpdate();
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
    @Override
    public String deleteEmployeeRecordInDB(long employeeId){
        System.out.println("deleteEmployeeRecordInDB() : Student Id: " + employeeId);
        try {
            pstmt = getConnection().prepareStatement("delete from employeetable where ID = "+employeeId);
            pstmt.executeUpdate();
            connObj.close();
        } catch(Exception sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }




}
