package com.mkyong.editor.faces;

import com.mkyong.editor.dao.*;
import com.mkyong.editor.domain.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;



@ManagedBean(name = "employees")
@SessionScoped
public class EmployeesBean {

    private long id;
    private String department;
    private String name;
    private String position;


    private EmployeeActions employeeActions = new InMemoryEmployeeActions();
    private GenerateEmployeeId generateEmployeeId = new InMemoryEmployeeId();


    public String clear() {
        setName(null);
        setPosition(null);
        setDepartment(null);
        return "createEmployee?faces-redirect=true";
    }

    public String moveToIndex() {
        return "index?faces-redirect=true";
    }


    public String addEmployeeDetails() {

        Employee employee = new Employee(this.name, this.position, this.department, this.generateEmployeeId.getUniqueId());
        employeeActions.addNewEmployee(employee);

        return "index?faces-redirect=true";
    }

    public String showEditWindow() {

        Employee employee = employeeActions.editEmployeeRecords(id);
        this.name = employee.getName();
        this.position = employee.getPosition();
        this.department = employee.getDepartment();

        return "editEmployee?faces-redirect=true";
    }

    public String editEmployeeRecords() {
        Employee employee = new Employee(this.name, this.position, this.department, this.id);
        employeeActions.updateEmployeeRecords(employee);
        return "index?faces-redirect=true";
    }

    public String deleteEmployeeRecords(long employeeId) {
        return employeeActions.deleteEmployeeRecords(employeeId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {

        return department;
    }

    public String getPosition() {
        return position;
    }


    public List<Employee> getEmployeeList() {
        return employeeActions.getAllEmployees();
    }


}
