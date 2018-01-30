package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;

import java.util.List;

public interface EmployeeActionsArrayList {

    boolean addEmployeeDetails(Employee employee);

    Employee editEmployeeRecords(Employee employee);

    Employee deleteEmployeeRecords(long id);

    Employee getEmployeeById(long employeeId);

    List<Employee> getAllEmployees();

    long findEmployee(long id);
}
