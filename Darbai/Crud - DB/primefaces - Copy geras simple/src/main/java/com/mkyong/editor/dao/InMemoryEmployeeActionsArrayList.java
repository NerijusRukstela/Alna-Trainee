package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryEmployeeActionsArrayList implements EmployeeActionsArrayList {
    private List<Employee> list = new ArrayList<>();

    @Override
    public boolean addEmployeeDetails(Employee employee) {
        list.add(employee);
        return true;
    }

    @Override
    public Employee deleteEmployeeRecords(long id) {
        for (Iterator<Employee> iter = list.iterator(); iter.hasNext(); ) {
            Employee employee = iter.next();
            long employeeId = employee.getId();
            if (employeeId == id) {
                iter.remove();
            }
        }
        return null;
    }

    public int findEmployeeIndexById(long id) {
        int index = 0;
        for (Employee employee : list) {
            if (employee.getId() == id) {
                break;
            }
            index++;
        }
        return index;
    }

    @Override
    public Employee editEmployeeRecords(Employee employee) {
        //todo T: nenaudoti for loopo, uztikrinti unikaluma - equals/hashcode, atsisakyti cia seteriu - Employee objektas turi moketi pats updatintis reiksmes
        int index = findEmployeeIndexById(employee.getId());
        list.set(index, employee);
        return null;
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
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


    public long findEmployee(long id){
        int newId = (int) id;
        for (Employee employee : list){
            if(employee.getId() == newId){
                return employee.getId();
            }
        }

        return Long.parseLong(null);
    }
}




