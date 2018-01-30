package com.mkyong.editor.faces;

import com.mkyong.editor.dao.*;
import com.mkyong.editor.domain.Employee;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "employees")
@SessionScoped
public class EmployeesBean {

    private long id;
    private String department;
    private String name;
    private String position;


    //    // Arraylist
//    private EmployeeActionsArrayList employeeActions = new InMemoryEmployeeActionsArrayList();
//    private GenerateEmployeeIdArrayList generateEmployeeId = new InMemoryEmployeeIdArrayList();
//
//
//    public String clear() {
//        setName(null);
//        setPosition(null);
//        setDepartment(null);
//        return "createEmployee?faces-redirect=true";
//    }
//
//    public String moveToIndex() {
//        return "index?faces-redirect=true";
//    }
//
//
//    public String addEmployeeDetails() {
//
//        Employee employee = new Employee(this.name, this.position, this.department, this.generateEmployeeId.getUniqueId());
//        employeeActions.addEmployeeDetails(employee);
//
//        return "index?faces-redirect=true";
//    }
//
//    public String showEditWindow() {
//
//        Employee employee = employeeActions.getEmployeeById(employeeActions.findEmployee(id));
//        this.name = employee.getName();
//        this.position = employee.getPosition();
//        this.department = employee.getDepartment();
//
//        return "editEmployee?faces-redirect=true";
//    }
//
//    public String editEmployeeRecords() {
//
//        Employee employee = new Employee(this.name, this.position, this.department, this.id);
//
//        employeeActions.editEmployeeRecords(employee);
//
//        return "index?faces-redirect=true";
//    }
//
//    public String showEditEmployeeRecords(long employeeId) {
//
//        return null;
//    }
//
//    public String deleteEmployeeRecords(long employeeId) {
//        employeeActions.deleteEmployeeRecords(employeeId);
//
//
//        return null;
//    }
//    public List<Employee> getEmployeeList() {
//        return employeeActions.getAllEmployees();
//    }


    //DB





    public ArrayList<EmployeesBean> employeesListFromDB;
    private EmployeeActionsDB employeeActionsDb = new InMemoryEmployeeDB();
    private GenerateEmployeeIdArrayList generateEmployeeId = new InMemoryEmployeeIdArrayList();

    public ArrayList employeesList() {
        return employeesListFromDB = employeeActionsDb.getEmployeesListFromDB();
    }

    public String saveEmployeeDetails(EmployeesBean newEmployeeObj) {
        generateEmployeeId.getUniqueId();
        employeeActionsDb.saveEmployeeDetailsInDB(newEmployeeObj);
        return "index.xhtml?faces-redirect=true";
    }

    public String editEmployeeRecord(long employeeId) {

        EmployeesBean employee = employeeActionsDb.editEmployeeRecordInDB(employeeId);
        this.name = employee.name;
        this.position = employee.position;
        this.department = employee.department;
        this.id = employee.id;


        return "editEmployee.xhtml?faces-redirect=true";
    }

    public String updateEmployeeDetails() {
//        long newId = this.id;
        Employee employee = new Employee(this.name, this.position, this.department, this.id);
        employeeActionsDb.updateEmployeeDetailsInDB(employee);
        return "index.xhtml?faces-redirect=true";
    }

    public String deleteEmployeeRecord(int employeeId) {
        return employeeActionsDb.deleteEmployeeRecordInDB(employeeId);
    }

        public String clear() {
        setName(null);
        setPosition(null);
        setDepartment(null);
        return "createEmployee?faces-redirect=true";
    }

        public String moveToIndex() {
        return "index?faces-redirect=true";
    }

    //S and G
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


}
