package com.mybatis.dao.mapper;

import com.mybatis.entity.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    Employee getEmployeeById(int id);

    Employee getEmployeeByIdAndName(int id, String lastName);

    Employee getEmployeeByIdAndName2(@Param ("id") int id, @Param ("lastName") String lastName);

    Map<String, Object> getEmployeeReturnMap(int id);

    /**
     * 多条记录封装在Map<String, Employee>
     * MapKey 告诉mybatis用Employee的哪个属性作为map的key
     */
    @MapKey ("id")
    Map<Integer, Employee> getEmployeeByGenderReturnMap(String gender);


    Employee getEmployeeByMap(Map<String, Object> map);

    int addEmployee(Employee employee);

    int deleteEmployeeById(int id);

    int updateEmployee(Employee employee);

    List<Employee> dynamicSql(Map<String, Object> map);

    Employee getEmployeeByDeptId(int i);

}
