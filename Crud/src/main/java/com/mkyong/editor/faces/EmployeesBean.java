package com.mkyong.editor.faces;

import com.mkyong.editor.dao.EmployeeActions;
import com.mkyong.editor.dao.InDbEmployeeActions;
import com.mkyong.editor.domain.Employee;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

@ManagedBean(name = "employees")
@SessionScoped
public class EmployeesBean implements Serializable {

    private long id;
    private String department;
    private String name;
    private String position;
    private Date date;
    private boolean save = false;
    private boolean add = false;
    private boolean nameError = false;
    private boolean positionError = false;
    private boolean departmentError = false;
    private boolean dateError = false;
    private EmployeeActions employeeActions;
    private LazyDataModel<Employee> lazyModel;







    @PostConstruct
    public void init() {
        employeeActions = new InDbEmployeeActions();
        lazyModel = new LazyModelHolder(employeeActions);
    }


    public LazyDataModel<Employee> getLazyModel() {

        return lazyModel;
    }

    public String changeTrueAddButton() {
        add = true;
        return null;
    }

    public String changeFalseSaveButton() {
        save = false;
        return null;
    }

    public void opendialog() {
        clear();
        nameError = false;
        positionError = false;
        departmentError = false;
        dateError = false;

        changeTrueAddButton();
        changeFalseSaveButton();
        RequestContext.getCurrentInstance().
                addCallbackParam("notValid123", true);

    }


    public String changeTrueSaveButton() {
        save = true;
        return null;
    }

    public String changeFalseAddButton() {
        add = false;
        return null;
    }

    public String opendialogFromEdit() {
        changeFalseAddButton();
        changeTrueSaveButton();

        return null;
    }

    public String clear() {
        this.name = null;
        this.position = null;
        this.department = null;
        this.date = null;
        return null;
    }

    public String exitAndClear() {
        this.name = null;
        this.position = null;
        this.department = null;
        RequestContext.getCurrentInstance().
                addCallbackParam("notValid9", true);
        return null;
    }




    public boolean addEmployee() throws ParseException {



        boolean success;
        int departmentLength = this.department.length();
        if (departmentLength == 0) {
            departmentError = true;
        } else {
            departmentError = false;
        }
        int nameLength = this.name.length();
        if (nameLength == 0) {
            nameError = true;
        } else {
            nameError = false;
        }
        int positionLength = this.position.length();
        if (positionLength == 0) {
            positionError = true;
        } else {
            positionError = false;
        }
        String dateString = String.valueOf(this.date);
        int dateLength = dateString.length();
        if (dateLength == 4) {
            dateError = true;
        } else {
            dateError = false;
        }
        if (departmentLength > 0 && nameLength > 0 && positionLength > 0 && dateLength > 4) {
            success = true;
            Employee employee = new Employee(this.name, this.position, this.department, this.date);
            employeeActions.addNewEmployee(employee);
//            getEmployeesList();
        } else {
            success = false;
        }

        RequestContext.getCurrentInstance().
                addCallbackParam("notValid", success);


        return true;
    }

    public String editEmployeeRecord(long employeeId) {

        opendialogFromEdit();
        nameError = false;
        positionError = false;
        departmentError = false;
        dateError = false;
        Employee employee = employeeActions.editEmployeeRecords(employeeId);
        this.name = employee.getName();
        this.position = employee.getPosition();
        this.department = employee.getDepartment();
        this.id = employee.getId();
        this.date = employee.getDate();

        return null;
    }

    public String updateEmployeeDetails() {

        boolean success;
        int departmentLength = this.department.length();
        if (departmentLength == 0) {
            departmentError = true;
        } else {
            departmentError = false;
        }
        int nameLength = this.name.length();
        if (nameLength == 0) {
            nameError = true;
        } else {
            nameError = false;
        }
        int positionLength = this.position.length();
        if (positionLength == 0) {
            positionError = true;
        } else {
            positionError = false;
        }
        String dateString = String.valueOf(this.date);
        int dateLength = dateString.length();
        if (dateLength == 4) {
            dateError = true;
        } else {
            dateError = false;
        }

        // & Bitwise operator - tikrina visas dalygas            && logical operator - jeigu pirmas neteisingas nebetikrina kitu
        if (departmentLength > 0 && nameLength > 0 && positionLength > 0 && dateLength > 4) {
            Employee employee = new Employee(this.name, this.position, this.department, this.id, this.date);
            employeeActions.updateEmployeeRecords(employee);
            success = true;
        } else {
            success = false;
        }
        RequestContext.getCurrentInstance().
                addCallbackParam("notValid", success);

        return null;
    }


    public void deleteEmployeeRecord(int employeeId) {

        employeeActions.deleteEmployeeRecords(employeeId);

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

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public boolean isNameError() {
        return nameError;
    }

    public void setNameError(boolean nameError) {
        this.nameError = nameError;
    }

    public boolean isPositionError() {
        return positionError;
    }

    public void setPositionError(boolean positionError) {
        this.positionError = positionError;
    }

    public boolean isDepartmentError() {
        return departmentError;
    }

    public void setDepartmentError(boolean departmentError) {
        this.departmentError = departmentError;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDateError() {
        return dateError;
    }

    public void setDateError(boolean dateError) {
        this.dateError = dateError;
    }

    public EmployeeActions getEmployeeActions() {
        return employeeActions;
    }

    public void setEmployeeActions(EmployeeActions employeeActions) {
        this.employeeActions = employeeActions;
    }

    //    public List<Employee> getEmployeesList() {
//
//        return employeeActions.getAllEmployees();
//    }
//    public void onSort(SortEvent event) {
//
//        String sortColumn = event.getSortColumn().getClientId();
//        cutColumnName = sortColumn.substring(14);
//
//    }

}
