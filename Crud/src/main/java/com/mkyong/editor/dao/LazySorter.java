package com.mkyong.editor.dao;

import com.mkyong.editor.domain.Employee;
import org.primefaces.model.SortOrder;

import java.util.Comparator;

public class LazySorter implements Comparator<Employee> {
    private String sortField;

    private SortOrder sortOrder;
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
    @Override
    public int compare(Employee car1, Employee car2) {
        try {
            Object value1 = Employee.class.getField(this.sortField).get(car1);
            Object value2 = Employee.class.getField(this.sortField).get(car2);

            int value = ((Comparable)value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
