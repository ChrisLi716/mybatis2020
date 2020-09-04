package com.mybatis.dao.mapper;

import com.mybatis.entity.Department;
import com.mybatis.entity.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper {
    Department getDeptById(int id);

    /**
     * 嵌套结果集关联查询
     */
    Department getDeptById2(int id);

    /**
     * 分步查询
     */
    Department getDeptByIdStep(int id);

    /**
     * 分步查询员工信息时传多个参数
     */
    Department getDeptByIdStep2(int id);


}
