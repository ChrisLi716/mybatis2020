package com.mybatis.dao.mapper;

import com.mybatis.entity.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeAnnotationMapper {

    @Select("select *  from employee  where id = #{id}")
    Employee getEmployeeById(int id);
}
