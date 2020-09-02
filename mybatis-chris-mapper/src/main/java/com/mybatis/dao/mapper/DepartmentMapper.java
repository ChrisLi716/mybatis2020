package com.mybatis.dao.mapper;

import com.mybatis.entity.Department;
import com.mybatis.entity.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper {
    Department getDeptById(int id);
}
