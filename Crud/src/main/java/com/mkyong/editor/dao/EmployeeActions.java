package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;
import org.primefaces.model.SortOrder;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface EmployeeActions {

    List<Employee> getAllEmployees();

    void addNewEmployee(Employee newEmployeeObj) throws ParseException;

    void updateEmployeeRecords(Employee updateEmployee);

    String deleteEmployeeRecords(long employeeId);

    Employee editEmployeeRecords(long employeeId);

    int getTotalNumberOfEmployees();

    List<Employee> getSelectedEmployees(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

}
