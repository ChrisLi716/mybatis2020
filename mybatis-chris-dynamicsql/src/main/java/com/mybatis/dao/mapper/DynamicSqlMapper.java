package com.mybatis.dao.mapper;

import com.mybatis.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicSqlMapper {

    List<Employee> getempsbyconditionIf(Employee employee);

    List<Employee> getempsbyconditionTrim(Employee employee);

    List<Employee> getempsbyconditionChoose(Employee employee);

    int updateEmp(Employee employee);

    List<Employee> getEmpsByConditionForeach(List<Integer> ids);

    int addEmpsInBatchInMySql(@Param ("emps") List<Employee> employees);


    int addEmpsInBatch2InMysql(@Param ("emps") List<Employee> employees);

    int addEmpsInBatchInOracle(@Param ("emps") List<Employee> employees);

    List<Employee> getInnerParameters(Employee emp);

    List<Employee> getEmpByBind(Employee emp);

    int addEmpsWithCondistionInclude(@Param ("emps") List<Employee> employees);


}


