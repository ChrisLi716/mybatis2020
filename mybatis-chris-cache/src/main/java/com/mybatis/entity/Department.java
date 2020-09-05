package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {

    private int id;
    private String name;
    private List<Employee> emps;

    public Department(int id) {
        this.id = id;
    }
}
