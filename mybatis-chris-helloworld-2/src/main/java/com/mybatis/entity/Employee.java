package com.mybatis.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("employeeAlias")
public class Employee {
    private int id;
    private String lastName;
    private String email;
    private String gender;
}
