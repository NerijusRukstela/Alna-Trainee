package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;
import org.primefaces.model.SortOrder;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "employeeService")
@ApplicationScoped
public class InDbEmployeeActions implements EmployeeActions {


    @Resource(name = "jdbc/LocalDatabaseName")
    private Context initCtx;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Connection conn;
    private DataSource dataSource;


//    public InDbEmployeeActions() {
//        try {
//            initCtx = new InitialContext();
//            this.dataSource = (DataSource) initCtx.lookup("java:/comp/env/jdbc/LocalDatabaseName");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public Connection getConnection() {
//        try {
//            conn = dataSource.getConnection();
//            return conn;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    private Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb?useSSL=false", "root", "root");
            Statement stmtObj = conn.createStatement();

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return conn;

    }

    @Override
    public String deleteEmployeeRecords(long employeeId) {
        System.out.println("deleteEmployeeRecordInDB() : Student Id: " + employeeId);
        try(PreparedStatement pstmt = getConnection().prepareStatement("delete from employeetable where ID = " + employeeId)) {

            pstmt.executeUpdate();
            conn.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewEmployee(Employee newStudentObj) {
        try(PreparedStatement pstmt = getConnection().prepareStatement("INSERT INTO employeetable (Name, Position, Department) VALUES (?, ?, ?)")) {


            pstmt.setString(1, newStudentObj.getName());
            pstmt.setString(2, newStudentObj.getPosition());
            pstmt.setString(3, newStudentObj.getDepartment());
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void updateEmployeeRecords(Employee updateStudentObj) {

        try(PreparedStatement pstmt = getConnection().prepareStatement("UPDATE employeetable SET Name=?, Position=?, Department=? WHERE id=?")) {


            pstmt.setString(1, updateStudentObj.getName());
            pstmt.setString(2, updateStudentObj.getPosition());
            pstmt.setString(3, updateStudentObj.getDepartment());
            pstmt.setLong(4, updateStudentObj.getId());
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    // TODO: 2018-01-24 kodel exucutina 2 kartus 
    public int getTotalNumberOfEmployees() {

        String query = "SELECT COUNT(*) FROM employeetable";

        int rowCount = 0;
        try(PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            rs.next();
            rowCount = rs.getInt(1);

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return rowCount;


    }

    private static void printFileJava7() throws IOException {

        try(FileInputStream input = new FileInputStream("file.txt")) {

            int data = input.read();
            while(data != -1){
                System.out.print((char) data);
                data = input.read();
            }
        }
    }

    @Override
    //todo: naujas metodas get employ nuo iki
    //todo: string format
    public List<Employee> getAllEmployees(){

        List<Employee> employeeList = new ArrayList<>();

        try(PreparedStatement pstmt = getConnection().prepareStatement("SELECT * FROM employeetable")) {
            pstmt.execute();
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
        //     if (sortField == ("name")) {
//            String sortName = "ASC";
//            if (sortOrder.name().equals("ASCENDING")) {
//                sortName = "ASC";
//            } else if (sortOrder.name().equals("DESCENDING")) {
//                sortName = "DESC";
//            }
//
//            query = String.format("SELECT * FROM employeetable ORDER BY %1s %2s", sortField, sortName);
//    }
        List<Employee> employeeList = new ArrayList<>();
        String query = String.format("SELECT * FROM employeetable LIMIT %1s, %2s", first, first + pageSize);

        try(PreparedStatement pstmt = getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()) {
            pstmt.execute();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("ID"));
                emp.setName(rs.getString("Name"));
                emp.setPosition(rs.getString("Position"));
                emp.setDepartment(rs.getString("Department"));
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
        try(PreparedStatement pstmt = getConnection().prepareStatement("select * from employeetable where ID = " + employeeId)) {


            rs = pstmt.executeQuery();
            if (rs != null) {
                rs.next();
                editRecord = new Employee();
                editRecord.setId((int) rs.getLong("id"));
                editRecord.setName(rs.getString("Name"));
                editRecord.setPosition(rs.getString("Position"));
                editRecord.setDepartment(rs.getString("Department"));
            }
            conn.close();
            return editRecord;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
