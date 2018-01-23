package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;
import org.primefaces.model.LazyDataModel;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "employeeService")
@ApplicationScoped
public class InDbEmployeeActions extends LazyDataModel<Employee> implements EmployeeActions {


    @Resource(name = "jdbc/LocalDatabaseName")
    private Context initCtx;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Connection conn;
    private DataSource dataSource;
    String sql = "from " + Employee.class.getName() + " user where 1=1 ";

    public InDbEmployeeActions() {
        try {
            initCtx = new InitialContext();
            this.dataSource = (DataSource) initCtx.lookup("java:/comp/env/jdbc/LocalDatabaseName");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        try {
            conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private Connection getConnection() {
//
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
        try {
            pstmt = getConnection().prepareStatement("delete from employeetable where ID = " + employeeId);
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewEmployee(Employee newStudentObj) {
        try {

            pstmt = getConnection().prepareStatement("INSERT INTO employeetable (Name, Position, Department) VALUES (?, ?, ?)");
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

        try {

            pstmt = getConnection().prepareStatement("UPDATE employeetable SET Name=?, Position=?, Department=? WHERE id=?");
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
    public List<Employee> getAllEmployees(String query, int pageSize, int first) {
        List<Employee> employeeList = new ArrayList<>();

        try {
            pstmt = getConnection().prepareStatement(query);
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

            int dataSize = employeeList.size();
//                   //paginate
//        if(dataSize > pageSize) {
//            try {
//                return employeeList.subList(first, first + pageSize);
//            }
//            catch(IndexOutOfBoundsException e) {
//                return employeeList.subList(first, first + (dataSize % pageSize));
//            }
//        }
////        else {
////            return employeeList;
//        }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeeList;


    }

    @Override
    public Employee editEmployeeRecords(long employeeId) {
        Employee editRecord = null;
        try {

            pstmt = getConnection().prepareStatement("select * from employeetable where ID = " + employeeId);
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
