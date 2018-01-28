package com.mkyong.editor.faces;

import com.mkyong.editor.dao.EmployeeActions;
import com.mkyong.editor.domain.Employee;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

public class LazyModelHolder extends LazyDataModel<Employee> {
    private transient EmployeeActions e;

    public LazyModelHolder(EmployeeActions e) {
        this.e = e;
    }

    @Override
    public List<Employee> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.setRowCount(e.getTotalNumberOfEmployees());
        return e.getSelectedEmployees(first, pageSize, sortField, sortOrder, filters);

    }
}
