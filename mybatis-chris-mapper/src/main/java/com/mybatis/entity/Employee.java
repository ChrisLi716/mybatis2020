package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
public class Employee {

    public Employee(String lastName, String email, String gender) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public Employee(int id, String lastName, String email, String gender) {
        this(lastName, email, gender);
        this.id = id;
    }

    private int id;
    private String lastName;
    private String email;
    private String gender;
    private Department dept;
}
