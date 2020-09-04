package com.mybatis.entity;

import com.mybatis.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private Department dept;


    public Employee(String lastName, String email, String gender) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public Employee(Integer id, String lastName, String email, String gender) {
        this(lastName, email, gender);
        this.id = id;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
