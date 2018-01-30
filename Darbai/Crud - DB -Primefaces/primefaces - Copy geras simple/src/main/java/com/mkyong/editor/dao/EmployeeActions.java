package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;

import java.util.List;

public interface EmployeeActions {

    List<Employee> getAllEmployees();

    void addNewEmployee(Employee newEmployeeObj);

    void updateEmployeeRecords(Employee updateEmployee);

    String deleteEmployeeRecords(long employeeId);

    Employee editEmployeeRecords(long employeeId);


}
