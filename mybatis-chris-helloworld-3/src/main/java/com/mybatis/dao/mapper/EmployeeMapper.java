package com.mybatis.dao.mapper;

import com.mybatis.entity.Employee;

public interface EmployeeMapper {
    Employee getEmployeeById(int id);

    Employee getEmployeeById2(int id);

    Employee testDefaultAlias(int id);

    Employee testSpecifiedAlias(int id);
}
