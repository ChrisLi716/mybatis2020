package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private int id;
    private String name;
    private List<Employee> emps;

    public Department(int id) {
        this.id = id;
    }
}
