package com.mkyong.editor.dao;

public class InMemoryEmployeeIdArrayList implements GenerateEmployeeIdArrayList {
    private static long id = 0;
    @Override
    public long getUniqueId() {
        id++;
        return id;
    }
}
