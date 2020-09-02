import com.mybatis.dao.mapper.EmployeeMapper;
import com.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperTest {

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 测试 addEmployee
     */
    @Test
    public void addEmployee() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee("Nancy", "Nancy@hormail.com", "F");
            employeeMapper.addEmployee(employee);
            System.out.println("add user id:" + employee.getId());
            openSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }


    /**
     * 测试 updateEmployee
     */
    @Test
    public void updateEmployee() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(3, "Hedy", "Henry_C002@gmail.com", "M");
            int result = employeeMapper.updateEmployee(employee);
            openSession.commit();
            System.out.println("result:" + result);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }

    /**
     * 测试 updateEmployee
     */
    @Test
    public void deleteEmployeeById() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            employeeMapper.deleteEmployeeById(2);
            openSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }


    /**
     * 测试 getEmployeeByIdAndName
     */
    @Test
    public void getEmployeeByIdAndName() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            // Employee employee = employeeMapper.getEmployeeByIdAndName(1, "Chris");

            // 测试以@Param("")命名的参数
            // Employee employee = employeeMapper.getEmployeeByIdAndName2(1, "Chris");

            // 测试map参数
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("p_id", 1);
            paramMap.put("p_lastName", "Chris");
            Employee employee = employeeMapper.getEmployeeByMap(paramMap);
            openSession.commit();
            System.out.println(employee.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }

    /**
     * 测试 testDynamicSql
     */
    @Test
    public void testDynamicSql() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);

            //map参数
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("p_gender", "F");
            paramMap.put("tableName", "Employee");
            paramMap.put("sortFieldName", "last_name");
            paramMap.put("sort", "ASC");

            List<Employee> employees = employeeMapper.dynamicSql(paramMap);
            for (Employee employee : employees) {
                System.out.println(employee.toString());
            }
            openSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }


    /**
     * 测试 testMapResult
     */
    @Test
    public void testMapResult() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);

            // 返回为单个对象
            Map<String, Object> employeeMap = employeeMapper.getEmployeeReturnMap(4);

            openSession.commit();
            for (Map.Entry<String, Object> entry : employeeMap.entrySet()) {
                System.out.println(entry.getKey() + ", " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }

    /**
     * 测试返回为多个对象
     */
    @Test
    public void testMapResult2() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);

            //返回为多个对象
            Map<Integer, Employee> employeeMap = employeeMapper.getEmployeeByGenderReturnMap("F");

            openSession.commit();
            for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
                System.out.println(entry.getKey() + ", " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }


}
