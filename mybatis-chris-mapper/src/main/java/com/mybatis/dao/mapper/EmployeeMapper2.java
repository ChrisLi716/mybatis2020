package com.mybatis.dao.mapper;

import com.mybatis.entity.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface EmployeeMapper2 {

    /**
     * 多条记录封装在Map<String, Employee>
     * MapKey 告诉mybatis用Employee的哪个属性作为map的key
     */
    @MapKey("id")
    Map<Integer, Employee> getEmployeeByGenderReturnMap(String gender);

    Employee getEmpAndDept(int empId);

    Employee getEmpAndDept2(int empId);

}
