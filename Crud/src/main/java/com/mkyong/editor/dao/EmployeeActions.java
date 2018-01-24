package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;
import org.primefaces.model.SortOrder;

import java.io.IOException;
import java.util.List;

public interface EmployeeActions {

    List<Employee> getAllEmployees() throws IOException;

    void addNewEmployee(Employee newEmployeeObj);

    void updateEmployeeRecords(Employee updateEmployee);

    String deleteEmployeeRecords(long employeeId);

    Employee editEmployeeRecords(long employeeId);

    int getTotalNumberOfEmployees();

    List<Employee> getSelectedEmployees(int first, int pageSize, String sortField, SortOrder sortOrder);

}
