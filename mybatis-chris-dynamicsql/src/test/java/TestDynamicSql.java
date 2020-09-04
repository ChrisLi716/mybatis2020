import com.mybatis.dao.mapper.DynamicSqlMapper;
import com.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDynamicSql {

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 测试IF
     */
    @Test
    public void testIF() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);
            // Employee employee = new Employee(1, "Chris", "sss@1734.com", "F");
            Employee employee = new Employee("Chris", "sss@1734.com", "F");
            List<Employee> emps = dynamicSqlMapper.getempsbyconditionIf(employee);
            openSession.commit();
            for (Employee emp : emps) {
                System.out.println(emp.toString());
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
     * 测试Trim
     */
    @Test
    public void testTrim() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);

            Employee employee = new Employee();
            employee.setId(1);
            employee.setLastName("Chris");
            employee.setEmail("sss@1734.com");

            List<Employee> emps = dynamicSqlMapper.getempsbyconditionTrim(employee);
            openSession.commit();
            for (Employee emp : emps) {
                System.out.println(emp.toString());
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
     * 测试Choose
     */
    @Test
    public void testChoose() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);

            Employee employee = new Employee();
            List<Employee> emps = dynamicSqlMapper.getempsbyconditionChoose(employee);
            openSession.commit();
            for (Employee emp : emps) {
                System.out.println(emp.toString());
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
     * 测试set
     */
    @Test
    public void testSet() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);

            Employee employee = new Employee();
            employee.setId(1);
            employee.setEmail("chris@gmail.com");

            int count = dynamicSqlMapper.updateEmp(employee);
            openSession.commit();
            System.out.println("update lines account:" + count);
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
     * 测试foreach
     */
    @Test
    public void testForeach() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);
            List<Employee> employees = dynamicSqlMapper.getEmpsByConditionForeach(Arrays.asList(1, 3, 4));
            openSession.commit();
            for (Employee employee : employees) {
                System.out.println(employee.toString());
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
