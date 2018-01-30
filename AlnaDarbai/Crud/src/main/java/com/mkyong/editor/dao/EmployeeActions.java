package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;

import java.util.List;

public interface EmployeeActions {

    List<Employee> getAllEmployees(String query, int pageSize, int first);

    void addNewEmployee(Employee newEmployeeObj);

    void updateEmployeeRecords(Employee updateEmployee);

    String deleteEmployeeRecords(long employeeId);

    Employee editEmployeeRecords(long employeeId);

//    List<Employee> retrieveUsers(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
//
//    int countUsers(Map<String, Object> filters);
}
