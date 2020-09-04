package com.mybatis.dao.mapper;

import com.mybatis.entity.Employee;

import java.util.List;

public interface DynamicSqlMapper {

    List<Employee> getempsbyconditionIf(Employee employee);

    List<Employee> getempsbyconditionTrim(Employee employee);

    List<Employee> getempsbyconditionChoose(Employee employee);

    int updateEmp(Employee employee);

    List<Employee> getEmpsByConditionForeach(List<Integer> ids);
}


