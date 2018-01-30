package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;

import java.util.List;

public interface EmployeeActions {

    List<Employee> getAllEmployees();

    void addNewEmployee(Employee employee);

    void updateEmployeeRecords(Employee employee);

    String deleteEmployeeRecords(long id);

    Employee editEmployeeRecords(long employeeId);



}
