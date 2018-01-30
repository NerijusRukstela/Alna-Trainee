package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryEmployeeActions implements EmployeeActions {
    private List<Employee> list = new ArrayList<>();
    private static long id = 0;
    private GenerateEmployeeId generatorId = new InMemoryEmployeeId();

    @Override
    public void addNewEmployee(Employee employee) {
        employee.setId(generatorId.getUniqueId());
        list.add(employee);

    }

    @Override
    public String deleteEmployeeRecords(long id) {
        for (Iterator<Employee> iter = list.iterator(); iter.hasNext(); ) {
            Employee employee = iter.next();
            long employeeId = employee.getId();
            if (employeeId == id) {
                iter.remove();
            }
        }
        return null;
    }

    @Override
    public void updateEmployeeRecords(Employee employee) {
        //todo T: nenaudoti for loopo, uztikrinti unikaluma - equals/hashcode, atsisakyti cia seteriu - Employee objektas turi moketi pats updatintis reiksmes
        int index = findEmployeeIndexById(employee.getId());
        list.set(index, employee);

    }

    @Override
    public Employee editEmployeeRecords(long employeeId) {
        for (Employee employee : list) {
            if (employee.getId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return list;
    }


    private int findEmployeeIndexById(long id) {
        int index = 0;
        for (Employee employee : list) {
            if (employee.getId() == id) {
                break;
            }
            index++;
        }
        return index;
    }
}
