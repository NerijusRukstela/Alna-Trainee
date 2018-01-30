package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;
import com.mkyong.editor.faces.EmployeesBean;

import java.sql.Connection;
import java.util.ArrayList;

public interface EmployeeActionsDB {
    Connection getConnection();
    ArrayList getEmployeesListFromDB();
    String saveEmployeeDetailsInDB(EmployeesBean newEmployeeObj);
    EmployeesBean editEmployeeRecordInDB(long employeeId);
    String updateEmployeeDetailsInDB(Employee updateStudentObj);
    String deleteEmployeeRecordInDB(long employeeId);
}
