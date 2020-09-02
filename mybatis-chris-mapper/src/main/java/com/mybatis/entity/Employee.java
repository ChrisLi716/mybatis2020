package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

  private int id;
  private String lastName;
  private String email;
  private String gender;
  private Department dept;


  public Employee(String lastName, String email, String gender) {
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
  }

  public Employee(int id, String lastName, String email, String gender) {
    this(lastName, email, gender);
    this.id = id;
  }


}
